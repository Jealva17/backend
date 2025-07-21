package com.batidos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batidos.backend.model.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    
}
