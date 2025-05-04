package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.FacturaDTO;
import com.example.inventorypharmacy.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<FacturaDTO> listarTodas() {
        return facturaService.listarTodas();
    }

    @GetMapping("/{id}")
    public FacturaDTO obtener(@PathVariable Long id) {
        return facturaService.obtenerPorId(id);
    }

    @PostMapping
    public FacturaDTO guardar(@RequestBody FacturaDTO dto) {
        return facturaService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        facturaService.eliminar(id);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarFacturaPdf(@PathVariable Long id) {
        ByteArrayInputStream pdfStream = facturaService.generarPdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=factura-" + id + ".pdf");

        try {
            byte[] pdfBytes = pdfStream.readAllBytes();
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}