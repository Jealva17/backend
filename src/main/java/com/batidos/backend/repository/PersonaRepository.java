package com.batidos.backend.repository;

import com.batidos.backend.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByCedulaAndTipo(String cedula, String tipo);

}
