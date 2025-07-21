package com.batidos.backend.controller;

import com.batidos.backend.dto.PedidoRequest;
import com.batidos.backend.dto.PedidoResponse;
import com.batidos.backend.model.*;
import com.batidos.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private VentaPedidoRepository ventaRepository;

    @Autowired
    private DetallePedidoRepository detalleRepository;

    /**
     * Endpoint para registrar un nuevo pedido.
     */
    @PostMapping
    public ResponseEntity<PedidoResponse> registrarPedido(@RequestBody PedidoRequest request) {
        // 1 Verificar si el cliente ya existe por cédula
        Persona persona;
        Optional<Persona> personaExistente = personaRepository.findByCedulaAndTipo(request.getCedula(), "CLIENTE");

        if (personaExistente.isPresent()) {
            persona = personaExistente.get();
            System.out.println(" Cliente existente: " + persona.getNombres());
        } else {
            persona = new Persona();
            persona.setNombres(request.getNombres());
            persona.setApellidos(request.getApellidos());
            persona.setCedula(request.getCedula());
            persona.setTelefono(request.getTelefono());
            persona.setTipo("CLIENTE");
            persona = personaRepository.save(persona);
            System.out.println(" Cliente nuevo creado: " + persona.getNombres());
        }

        // 2 Crear una nueva venta
        VentaPedido venta = new VentaPedido();
        venta.setFecha(new Date(System.currentTimeMillis())); // Fecha actual
        venta.setEstado("Enviado"); // Estado inicial
        venta.setIdCliente(persona.getId());
        venta.setPrecioTotal(0.0); // Se actualiza luego
        venta.setIdTrabajador(null);

        // 3 Procesar comprobante (si existe)
        String base64 = request.getComprobanteBase64();
        if (base64 != null && !base64.isEmpty()) {
            System.out.println(" Comprobante recibido, longitud base64: " + base64.length());
            try {
                if (base64.contains(",")) {
                    base64 = base64.split(",")[1];
                }
                byte[] comprobanteBytes = Base64.getDecoder().decode(base64);
                System.out.println(" Bytes decodificados: " + comprobanteBytes.length);
                venta.setComprobantePago(comprobanteBytes);
            } catch (IllegalArgumentException e) {
                System.out.println(" Error decodificando comprobante");
                return ResponseEntity.badRequest().body(new PedidoResponse(null, "Comprobante inválido."));
            }
        } else {
            System.out.println(" No se recibió comprobante (base64 vacío o null)");
        }

        // Guardar la venta preliminarmente
        venta = ventaRepository.save(venta);
        System.out.println(" Venta guardada preliminarmente con ID: " + venta.getId());

        // 4 Crear detalles del pedido
        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0.0;

        for (PedidoRequest.DetalleDTO d : request.getProductos()) {
            DetallePedido detalle = new DetallePedido();
            detalle.setIdProducto(d.getIdProducto());
            detalle.setCantidad(d.getCantidad());
            detalle.setSubtotal(d.getSubtotal());
            detalle.setVenta(venta); // Asocia al pedido
            total += d.getSubtotal();
            detalles.add(detalle);
        }

        detalleRepository.saveAll(detalles);
        System.out.println(" Detalles guardados: " + detalles.size());

        // 5 Actualizar el total de la venta
        venta.setPrecioTotal(total);
        ventaRepository.save(venta);
        System.out.println(" Venta actualizada con total: " + total);

        // 6 Crear la respuesta
        PedidoResponse response = new PedidoResponse(venta.getId(), "Pedido registrado correctamente");
        System.out.println(" Pedido registrado correctamente con ID: " + venta.getId());

        // 7 Retornar respuesta
        return ResponseEntity.ok(response);
    }
}
