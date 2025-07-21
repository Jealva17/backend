package com.batidos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batidos.backend.model.VentaPedido;

public interface VentaPedidoRepository extends JpaRepository<VentaPedido, Long> {
    
}
