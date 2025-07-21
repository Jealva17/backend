package com.batidos.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PedidoRequest {
    private String nombres;
    private String apellidos;
    private String cedula;
    private String telefono;

    @JsonProperty("comprobante")
    private String comprobanteBase64; // se enlaza con el campo del JSON

    private List<DetalleDTO> productos;

    public static class DetalleDTO {
        private Long idProducto;
        private int cantidad;
        private double subtotal;

        public Long getIdProducto() { return idProducto; }
        public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }

        public double getSubtotal() { return subtotal; }
        public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getComprobanteBase64() { return comprobanteBase64; }
    public void setComprobanteBase64(String comprobanteBase64) { this.comprobanteBase64 = comprobanteBase64; }

    public List<DetalleDTO> getProductos() { return productos; }
    public void setProductos(List<DetalleDTO> productos) { this.productos = productos; }
}
