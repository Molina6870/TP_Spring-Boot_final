package com.utn.productos.service;


import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    // Inyección de dependencia por constructor
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    //Creamos el producto
    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    //Retornamos la lista de todos los productos
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }

    //Obtener por Id
    public Optional<Producto> obtenerPorId(Long id){
        return productoRepository.findById(id);
    }

    //Obtenemos los productos por Categoria
    public List<Producto> obtenerPorCategoria(Categoria categoria){
        return productoRepository.findByCategoria(categoria);
    }

    // Actualizar producto completo
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> productoExistente = productoRepository.findById(id);

        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
            producto.setCategoria(productoActualizado.getCategoria());
            return productoRepository.save(producto);
        } else {
            // Por ahora lanzamos RuntimeException genérica (en la parte 5 haremos una personalizada)
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    // Actualizar solo el stock
    public Producto actualizarStock(Long id, Integer nuevoStock) {
        Optional<Producto> productoExistente = productoRepository.findById(id);

        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setStock(nuevoStock);
            return productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    // Eliminar producto
    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
    }

}
