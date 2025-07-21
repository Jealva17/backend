package com.batidos.backend.dto;

/**
 * DTO (Data Transfer Object) que se usa para enviar la respuesta
 * al cliente cuando se registra un pedido.
 */
public class PedidoResponse {

    private Long id;        
    private String mensaje; 

    // Constructor vacío (necesario para frameworks como Jackson)
    public PedidoResponse() {}

    
    public PedidoResponse(Long id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
