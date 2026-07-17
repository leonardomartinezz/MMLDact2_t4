package com.a1.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @GetMapping("/leonardo")
    public String obtenerAutor() {
        return "Instancia corriendo - Proyecto de: Leonardo Martinez";
    }

    @GetMapping("/version-2026")
    public int obtenerAñoProyecto() {
        return 2026;
    }
}
