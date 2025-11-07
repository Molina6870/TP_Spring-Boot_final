package com.utn.productos.controller;


import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.exception.ProductoNotFoundException;
import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.*;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Productos", description = "API REST para la gestión de productos del e-commerce")
@RestController
@RequestMapping("/api/productos")

public class ProductoController {

    private final ProductoService productoService;

    //Agregamos Producto Service por constructor
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    // Get: Listar todos los productos
    @Operation(summary = "Listar todos los productos", description = "Obtiene todos los productos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<ProductoResponseDTO> lista = productoService.obtenerTodos()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // Get: Obtener producto por ID
    @Operation(summary = "Obtener producto por ID", description = "Busca un producto existente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));
        return ResponseEntity.ok(convertirAResponseDTO(producto));
    }


    // Get: Filtrar por categoría
    @Operation(summary = "Filtrar productos por categoría", description = "Obtiene todos los productos que pertenecen a una categoría específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista filtrada correctamente")
    })
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        List<ProductoResponseDTO> lista = productoService.obtenerPorCategoria(categoria)
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // POST: Crear producto
    @Operation(summary = "Crear nuevo producto", description = "Crea un producto y lo guarda en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO dto) {
        Producto producto = convertirADominio(dto);
        Producto guardado = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponseDTO(guardado));
    }

    // Put: Actualizar producto completo
    @Operation(summary = "Actualizar producto", description = "Actualiza todos los campos de un producto existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error de validación")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO dto) {

        Producto actualizado = productoService.actualizarProducto(id, convertirADominio(dto));
        return ResponseEntity.ok(convertirAResponseDTO(actualizado));
    }

    // Patch: Actualizar solo el stock
    @Operation(summary = "Actualizar stock", description = "Modifica únicamente el valor de stock de un producto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Valor de stock inválido")
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarStockDTO dto) {

        Producto actualizado = productoService.actualizarStock(id, dto.getStock());
        return ResponseEntity.ok(convertirAResponseDTO(actualizado));
    }

    // Delete: Eliminar producto
    @Operation(summary = "Eliminar producto", description = "Elimina un producto existente según su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para conversión
    private ProductoResponseDTO convertirAResponseDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    private Producto convertirADominio(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }

}
