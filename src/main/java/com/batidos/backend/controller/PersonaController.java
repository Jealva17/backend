package com.batidos.backend.controller;

import com.batidos.backend.model.Persona;
import com.batidos.backend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping
    public Persona crearCliente(@RequestBody Persona persona) {
        persona.setTipo("CLIENTE");
        return personaRepository.save(persona);
    }

    
}

