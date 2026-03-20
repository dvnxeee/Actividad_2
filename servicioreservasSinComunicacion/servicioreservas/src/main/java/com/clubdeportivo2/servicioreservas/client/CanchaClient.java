package com.clubdeportivo2.servicioreservas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.clubdeportivo2.servicioreservas.model.dto.Cancha;

@FeignClient(name = "canchas-service", url = "http://localhost:8081")
public interface CanchaClient {

    @GetMapping("/api/canchas/{id}")
    Cancha obtenerCancha(@PathVariable Long id);
}