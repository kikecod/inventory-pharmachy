package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/resumen")
    public ResponseEntity<Map<String, Object>> resumen() {
        return ResponseEntity.ok(dashboardService.getResumenDashboard());
    }

    @GetMapping("/ventas-por-categoria")
    public ResponseEntity<List<Map<String, Object>>> ventasPorCategoria() {
        return ResponseEntity.ok(dashboardService.getVentasPorCategoria());
    }
}
