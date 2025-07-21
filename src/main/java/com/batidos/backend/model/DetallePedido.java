package com.batidos.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DETALLE_PEDIDO")
public class DetallePedido {
    

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Long id;

    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name = "SUBTOTAL")
    private double subtotal;

    @Column(name = "FK_ID_PRODUCTO")
    private Long idProducto;

    @ManyToOne
@JoinColumn(name = "FK_ID_VENTA")
@JsonBackReference
private VentaPedido venta;







    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public VentaPedido getVenta() { return venta; }
    public void setVenta(VentaPedido venta) { this.venta = venta; }
}
