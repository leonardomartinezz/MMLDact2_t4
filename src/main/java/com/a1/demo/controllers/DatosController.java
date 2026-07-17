package com.a1.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/datos")
public class DatosController {

    // Endpoint b) Regresa una lista simulada de materias o datos personales
    @GetMapping("/materias")
    public List<String> obtenerMaterias() {
        return List.of(
            "Arquitectura de Computadoras", 
            "Desarrollo Web", 
            "Base de Datos", 
            "Sistemas Operativos"
        );
    }
}
