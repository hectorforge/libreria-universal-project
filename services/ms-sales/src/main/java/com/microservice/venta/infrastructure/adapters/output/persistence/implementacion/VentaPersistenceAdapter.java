package com.microservice.venta.infrastructure.adapters.output.persistence.implementacion;

import com.microservice.venta.application.ports.output.VentaPersistencePort;
import com.microservice.venta.domain.exception.VentaNotException;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DniResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.SunatResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.feign.DetalleVentaCompletaFeign;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.feign.VentaCompletaFeign;
import com.microservice.venta.infrastructure.adapters.output.client.response.DataResponse;
import com.microservice.venta.infrastructure.adapters.output.client.response.ProductoFeignClient;
import com.microservice.venta.infrastructure.adapters.output.client.response.ProductoResponse;
import com.microservice.venta.infrastructure.adapters.output.client.response.SunatClient;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.FacturacionEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.PagoEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.VentaEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.mapper.VentaPersistenceMapper;
import com.microservice.venta.infrastructure.adapters.output.persistence.repository.FacturacionRepository;
import com.microservice.venta.infrastructure.adapters.output.persistence.repository.PagoRepository;
import com.microservice.venta.infrastructure.adapters.output.persistence.repository.VentaRepository;
import com.microservice.venta.domain.enums.Estado;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class VentaPersistenceAdapter implements VentaPersistencePort {

    private final VentaRepository repository;
    private final PagoRepository pagoRepository;
    private final FacturacionRepository facturacionRepository;
    private final SunatClient sunatClient;
    private final ProductoFeignClient productoFeignClient;

    @Override
    public Optional<VentaModel> obtenerVentaPorId(UUID id) {
        return repository.obtenerVentaPorId(id)
                .map(VentaPersistenceMapper::toResponse);
    }

    @Override
    public List<VentaModel> listarVentas() {
        return repository.listarVentas()
                .stream()
                .map(VentaPersistenceMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public VentaModel guardarVenta(VentaModel ventaModel) {

        VentaEntity ventaNueva = VentaPersistenceMapper.toEntity(ventaModel);

        String codigo = generarCodigoVenta();
        Double totalVenta = calcularTotalVenta(ventaNueva);
        LocalDate fechaActual = LocalDate.now();

        ventaNueva.setCodigo(codigo);
        ventaNueva.setTotal(totalVenta);
        ventaNueva.setFecha(fechaActual);
        ventaNueva.setActivo(true);
        ventaNueva.setEstado(Estado.CONFIRMADO);

        if (ventaNueva.getDetalles() != null) {
            ventaNueva.getDetalles().forEach(detalle -> {
                double subtotal = detalle.getPrecioUnitario() * detalle.getCantidad();
                detalle.setSubtotal(subtotal);
                detalle.setVenta(ventaNueva);
            });
        }

        VentaEntity ventaGuardada = repository.save(ventaNueva);

        // Crear pago directamente con la entidad persistida
        PagoEntity nuevoPago = PagoEntity.builder()
                .venta(ventaGuardada)
                .monto(ventaGuardada.getTotal())
                .metodoPago("EFECTIVO")
                .fechaPago(LocalDate.now())
                .activo(true)
                .estado("PAGADO")
                .build();

        pagoRepository.save(nuevoPago);

        return VentaPersistenceMapper.toResponse(ventaGuardada);
    }


    @Override
    @Transactional
    public VentaModel actualizarVenta(UUID id, VentaModel ventaModel) {

        VentaEntity venta = repository.findById(id)
                .orElseThrow(() ->
                        new VentaNotException("Venta no encontrada con ID: " + id));

        venta.setClienteId(ventaModel.getClienteId());
        venta.setTotal(ventaModel.getTotal());
        venta.setEstado(ventaModel.getEstado());

        venta.setActivo(venta.getEstado() != Estado.CANCELADO);

        return VentaPersistenceMapper.toResponse(
                repository.save(venta)
        );
    }

    @Override
    @Transactional
    public void cancelarVenta(UUID id) {

        VentaEntity venta = repository.findById(id).orElseThrow(
                () -> new VentaNotException("Venta no encontrada con ID: " + id));

        venta.setActivo(false);
        venta.setEstado(Estado.CANCELADO);

        // Eliminar pago si existe
        pagoRepository.obtenerPagoPorIdVenta(venta.getId())
                .ifPresent(pago -> pagoRepository.deleteById(pago.getId()));

        // Anular la factura asociada a la venta cancelada
        // Anular factura si existe
        facturacionRepository.obtenerFacturaPorIdVenta(venta.getId())
                .ifPresent(factura -> {
                    factura.setActivo(false);
                    factura.setEstado("ANULADA");
                    facturacionRepository.save(factura);
                });

        repository.save(venta);
    }

    @Override
    public List<VentaModel> listarVentasPorCliente(UUID idCliente) {
        return repository.obtenerVentaPorIdCliente(idCliente)
                .stream()
                .map(VentaPersistenceMapper::toResponse)
                .toList();
    }

    @Override
    public List<VentaModel> listarVentasPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return repository.obtenerVentasPorFechas(fechaDesde, fechaHasta)
                .stream()
                .map(VentaPersistenceMapper::toResponse)
                .toList();
    }

    @Override
    public List<VentaCompletaFeign> listarVentasCompletasFeign() {

//        var listaVentas = repository.listarVentas();
//
//        return listaVentas.stream()
//                .map(venta -> VentaCompletaFeign.builder()
//                        .id(venta.getId())
//                        .codigo(venta.getCodigo())
//                        .clienteId(venta.getClienteId())
//                        .total(venta.getTotal())
//                        .fecha(venta.getFecha())
//                        .estado(venta.getEstado())
//                        .detalles(
//                                venta.getDetalles().stream()
//                                        .map(detalle -> {
//
//                                            DataResponse<ProductoResponse> response =
//                                                    productoFeignClient.obtenerProductoPorId(detalle.getProductoId());
//
//                                            ProductoResponse producto = response.getData();
//
//                                            return DetalleVentaCompletaFeign.builder()
//                                                    .id(detalle.getId())
//                                                    .productoId(detalle.getProductoId())
//                                                    .nombreProducto(producto.getNombreProducto())
//                                                    .precioUnitario(producto.getPrecioActualProducto())
//                                                    .cantidad(detalle.getCantidad())
//                                                    .subtotal(detalle.getSubtotal())
//                                                    .build();
//                                        })
//                                        .toList()
//                        )
//                        .build()
//                )
//                .toList();

        return null;
    }

    //Métodos privados y auxiliares
    private String generarCodigoVenta() {
        int anio = Year.now().getValue();
        long correlativo = repository.contarVentasPorAnio(anio) + 1;
        return anio + "-" + String.format("%04d", correlativo);
    }

    public static Double calcularTotalVenta(VentaEntity ventaEntity) {
        return ventaEntity.getDetalles()
                .stream()
                .mapToDouble(detalle -> detalle.getPrecioUnitario() * detalle.getCantidad())
                .sum();
    }

    // Validar Ruc/DNI Cliente
    public Mono<SunatResponse> consultarRuc(String ruc) {
        return sunatClient.obtenerUsuarioSunat(ruc);
    }

    public Mono<DniResponse> consultarDni(String dni) {
        return sunatClient.ObtenerUsuarioReniec(dni);
    }

}
