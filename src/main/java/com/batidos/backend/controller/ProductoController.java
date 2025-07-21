package com.batidos.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.batidos.backend.model.Productos;
import com.batidos.backend.repository.ProductosRepository;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductosRepository productosRepository;

    public ProductoController(ProductosRepository productoRepository) {
        this.productosRepository = productoRepository;
    }

    @GetMapping
    public List<Productos> listarProductos() {
        return productosRepository.findAll();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Productos> listarPorCategoria(@PathVariable String categoria) {
        return productosRepository.findByCategoria(categoria);
    }

    @PostMapping
    public Productos crearProducto(@RequestBody Productos producto) {
        return productosRepository.save(producto);
    }

    @GetMapping("/{id}")
    public Productos obtenerProducto(@PathVariable Long id) {
        return productosRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productosRepository.deleteById(id);
    }
}
