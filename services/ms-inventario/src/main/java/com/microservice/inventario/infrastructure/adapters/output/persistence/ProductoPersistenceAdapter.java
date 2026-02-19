package com.microservice.inventario.infrastructure.adapters.output.persistence;

import com.microservice.inventario.application.ports.output.ProductoPersistencePort;
import com.microservice.inventario.application.service.InventarioService;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.ProductoRestMapperManual;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoInventarioResponse;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.ProductoPersistenceMapperManual;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.InventarioRepository;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.ProductoRepository;
import com.microservice.inventario.shared.response.OperationResult;
import com.microservice.inventario.shared.response.pagination.PaginaResult;
import com.microservice.inventario.shared.response.pagination.PaginacionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductoPersistenceAdapter implements ProductoPersistencePort { // aca implementara el adapter de puerto de salida

    private final ProductoRepository repository;
    private final InventarioRepository inventarioRepository;
    private final ProductoPersistenceMapperManual mapperManual;
    private final InventarioService inventarioService;
    private final ProductoRestMapperManual restMapperManual;

    @Override
    public Optional<Producto> findById(UUID id) {
        return repository.findById(id)
                .map(ProductoPersistenceMapperManual::toModel);
    }

    @Override
    public List<Producto> findAll() {
        return repository.listarProductos()
                .stream()
                .map(ProductoPersistenceMapperManual::toModel)
                .toList();
    }

    @Override
    public List<Producto> findByCategoriaId(UUID categoriaId) {
        return repository.findByCategoriaId(categoriaId)
                .stream()
                .map(ProductoPersistenceMapperManual::toModel)
                .toList();
    }

    @Override
    public Producto save(Producto producto) {
        return ProductoPersistenceMapperManual.toModel(
                repository.save(ProductoPersistenceMapperManual.toEntity(producto)
                )
        );
    }
    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ProductoInventarioResponse> obtenerProductoInventarioPorId(UUID id) {
        return null;
    }

    @Override
    public List<Producto> registrarVariosProductos(List<Producto> productos) {

        List<ProductoEntity> listaProductos = productos
                .stream()
                .map(ProductoPersistenceMapperManual::toEntity)
                .toList();

        List<ProductoEntity> productosGuardados = repository.saveAll(listaProductos);

        return productosGuardados
                .stream()
                .map(ProductoPersistenceMapperManual::toModel)
                .toList();
    }

    //paginacion

    @Override
    public OperationResult<PaginaResult<Producto>> listarPaginado(PaginacionRequest request) {

        Sort sort = request.isAscendente()
                ? Sort.by(request.getOrdenarPor()).ascending()
                : Sort.by(request.getOrdenarPor()).descending();

        Pageable pageable = PageRequest.of(
                request.getPagina(),
                request.getTamanio(),
                sort
        );

        Page<ProductoEntity> page = repository.findAll(pageable);

        PaginaResult<Producto> pagina = PaginaResult.of(
                page.getContent()
                        .stream()
                        .map(ProductoPersistenceMapperManual::toModel)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );

        return OperationResult.successPagination(
                pagina,
                "Productos paginados correctamente",
                200
        );
    }
}
