package com.microservice.venta.infrastructure.adapters.output.persistence.implementacion;

import com.microservice.venta.application.ports.input.VentaServicePort;
import com.microservice.venta.application.ports.output.VentaPersistencePort;
import com.microservice.venta.domain.exception.VentaNotException;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.VentaReactivoResponse;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.VentaEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.mapper.VentaPersistenceMapper;
import com.microservice.venta.infrastructure.adapters.output.persistence.repository.VentaRepository;
import com.microservice.venta.domain.enums.Estado;
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

        repository.save(ventaNueva);

        //Todo: Falta implementar la lógica para pago

        return VentaPersistenceMapper.toResponse(ventaNueva);
    }

    @Override
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
    public void cancelarVenta(UUID id) {

        VentaEntity venta = repository.findById(id).orElseThrow(
                () -> new VentaNotException("Venta no encontrada con ID: " + id));

        venta.setActivo(false);
        venta.setEstado(Estado.CANCELADO);

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
    public Mono<VentaReactivoResponse> obtenerVentaReactivoPorId(UUID id) {
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
}
