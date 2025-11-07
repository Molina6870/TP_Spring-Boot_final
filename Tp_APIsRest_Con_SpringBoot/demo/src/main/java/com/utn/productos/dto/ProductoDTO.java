package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class ProductoDTO {

    @Schema(description = "Nombre del producto", example = "Smartphone Samsung A55")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Schema(description = "Descripción breve del producto", example = "Pantalla AMOLED de 6.7 pulgadas")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "299999.99")
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    private Double precio;

    @Schema(description = "Stock disponible", example = "15")
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    @NotNull(message = "La categoria es obligatoria")
    private Categoria categoria;

    //Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

}
