package com.batidos.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batidos.backend.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
    List<Productos> findByCategoria(String categoria);

}
