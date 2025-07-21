package com.batidos.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batidos.backend.model.VentaPedido;
import com.batidos.backend.repository.VentaPedidoRepository;

@RestController
@RequestMapping("/api/venta")
public class VentaPedidoController {

    private final VentaPedidoRepository ventaPedidoRepository;

    public VentaPedidoController(VentaPedidoRepository ventaRepository) {
        this.ventaPedidoRepository = ventaRepository;
    }

    @GetMapping
    public List<VentaPedido> listarVentas() {
        return ventaPedidoRepository.findAll();
    }

    @PostMapping
    public VentaPedido crearVenta(@RequestBody VentaPedido venta) {
        return ventaPedidoRepository.save(venta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> obtenerEstadoVenta(@PathVariable Long id) {
        Optional<VentaPedido> ventaOpt = ventaPedidoRepository.findById(id);
        if (ventaOpt.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("estado", ventaOpt.get().getEstado());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("estado", "desconocido");
            return ResponseEntity.ok(response);
        }
    }

    

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        ventaPedidoRepository.deleteById(id);
    }

}
