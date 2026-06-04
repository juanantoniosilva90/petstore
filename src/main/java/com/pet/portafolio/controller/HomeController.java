package com.pet.portafolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok(
            "Bienvenido a la API de Mascotas<br/><br/>" +
            "Endpoints disponibles:<br/>" +
            "GET /api/pet/{petId} - Obtener mascota desde Petstore<br/>" +
            "POST /api/pet - Crear mascota en Petstore<br/>" +
            "<br/>" +
            "Ejemplos de IDs existentes en Petstore: 888, 999<br/>" +
            "Primero crea una mascota con POST y luego consultala con GET<br/>" +
            "<br/>" +
            "Consola H2: /h2-console<br/>" +
            "Swagger UI: /swagger-ui/index.html"
        );
    }
}
