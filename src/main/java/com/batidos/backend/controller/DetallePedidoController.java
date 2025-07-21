package com.batidos.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batidos.backend.model.DetallePedido;
import com.batidos.backend.repository.DetallePedidoRepository;

@RestController
@RequestMapping("/api/detalle_pedidos")
public class DetallePedidoController {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoController(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @GetMapping
    public List<DetallePedido> listarDetalles() {
        return detallePedidoRepository.findAll();
    }
 
    @PostMapping
    public DetallePedido crearDetalle(@RequestBody DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    @GetMapping("/{id}")
    public DetallePedido obtenerDetalle(@PathVariable Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalle(@PathVariable Long id) {
        detallePedidoRepository.deleteById(id);
    }
    
}
