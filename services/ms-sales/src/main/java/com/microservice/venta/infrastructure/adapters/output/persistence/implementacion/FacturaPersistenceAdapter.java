package com.microservice.venta.infrastructure.adapters.output.persistence.implementacion;

import com.microservice.venta.application.ports.output.FacturacionPersistencePort;
import com.microservice.venta.domain.model.FacturacionModel;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.DetalleFacturaEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.FacturacionEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.VentaEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.mapper.FacturacionPersistenceMapper;
import com.microservice.venta.infrastructure.adapters.output.persistence.repository.FacturacionRepository;
import com.microservice.venta.infrastructure.adapters.output.persistence.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class FacturaPersistenceAdapter implements FacturacionPersistencePort {

    private final FacturacionRepository repository;
    private final VentaRepository ventaRepository;

    @Override
    public Optional<FacturacionModel> obtenerFacturacionPorId(UUID id) {
        return repository.obtenerFacturaPorId(id)
                .map(FacturacionPersistenceMapper::toResponse);
    }

    @Override
    public List<FacturacionModel> listarFacturaciones() {
        return repository.listarFacturasTrue()
                .stream()
                .map(FacturacionPersistenceMapper::toResponse)
                .toList();
    }

    @Override
    public FacturacionModel generarFacturacion(FacturacionModel facturacionModel) {

        FacturacionEntity facturaNueva = FacturacionPersistenceMapper.toEntity(facturacionModel);

        String numFactura = generarCodigoVenta();
        LocalDate fechaActual = LocalDate.now();

        VentaEntity venta = ventaRepository.obtenerVentaPorId(facturacionModel.getVenta().getId())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + facturacionModel.getVenta().getId()));

        facturaNueva.setNumeroFactura(numFactura);
        facturaNueva.setFechaFactura(fechaActual);
        facturaNueva.setVenta(venta);
        facturaNueva.setMetodoPago("TRANSFERENCIA");
        facturaNueva.setTotal(venta.getTotal());
        facturaNueva.setSubtotal(venta.getTotal() / 1.18);
        facturaNueva.setImpuestos(venta.getTotal() - (venta.getTotal() / 1.18));
        facturaNueva.setActivo(true);
        facturaNueva.setEstado("GENERADA");

        List<DetalleFacturaEntity> listaFacturaDetalles = new ArrayList<DetalleFacturaEntity>();

        venta.getDetalles().forEach(detalleVenta -> {

            DetalleFacturaEntity detalleFactura = new DetalleFacturaEntity();

            detalleFactura.setCantidad(detalleVenta.getCantidad());
            detalleFactura.setDescripcion(detalleVenta.getProductoId() + "-");
            detalleFactura.setValorUnitario(detalleVenta.getPrecioUnitario());
            detalleFactura.setSubtotal(detalleVenta.getSubtotal());
            detalleFactura.setFacturacion(facturaNueva);

            listaFacturaDetalles.add(detalleFactura);
        });

        facturaNueva.setDetalles(listaFacturaDetalles);

        return FacturacionPersistenceMapper.toResponse(repository.save(facturaNueva));
    }

    @Override
    public Optional<FacturacionModel> obtenerFacturacionPorNumero(String numFactura) {
        return repository.obtenerFacturaPorCodigoFactura(numFactura)
                .map(FacturacionPersistenceMapper::toResponse);
    }

    @Override
    public void cancelarFacturacion(UUID id) {
        FacturacionEntity factura = repository.obtenerFacturaPorId(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));

        factura.setActivo(false);
        factura.setEstado("CANCELADA");
        repository.save(factura);
    }

    @Override
    public List<FacturacionModel> listarFacturacionPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return repository.listarFacturasPorFechas(fechaDesde, fechaHasta)
                .stream()
                .map(FacturacionPersistenceMapper::toResponse)
                .toList();
    }

    //Métodos privados y auxiliares
    private String generarCodigoVenta() {
        int anio = Year.now().getValue();
        long correlativo = repository.contarFacturasPorAnio(anio) + 1;
        return "F-" + anio + "-" + String.format("%04d", correlativo);
    }
}
