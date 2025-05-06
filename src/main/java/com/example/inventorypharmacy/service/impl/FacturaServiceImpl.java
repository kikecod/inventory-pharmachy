package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.FacturaDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.FacturaService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepo;

    @Autowired
    private VentaRepository ventaRepo;

    private FacturaDTO toDTO(Factura f) {
        return new FacturaDTO(
                f.getIdFactura(),
                f.getVenta().getIdVenta(),
                f.getRfcCliente(),
                f.getRazonSocial(),
                f.getDireccionFiscal(),
                f.getFechaEmision(),
                f.getSucursal().getIdSucursal());
    }

    private Factura toEntity(FacturaDTO dto) {
        Factura f = new Factura();
        f.setIdFactura(dto.getIdFactura());
        f.setVenta(ventaRepo.findById(dto.getIdVenta()).orElseThrow());
        f.setRfcCliente(dto.getRfcCliente());
        f.setRazonSocial(dto.getRazonSocial());
        f.setDireccionFiscal(dto.getDireccionFiscal());
        f.setFechaEmision(dto.getFechaEmision());
        f.getSucursal().setIdSucursal(dto.getIdSucursal());
        return f;
    }

    @Override
    public List<FacturaDTO> listarTodas() {
        return facturaRepo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public FacturaDTO obtenerPorId(Long id) {
        return facturaRepo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public FacturaDTO guardar(FacturaDTO dto) {
        Factura saved = facturaRepo.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public void eliminar(Long id) {
        facturaRepo.deleteById(id);
    }

    @Override
    public ByteArrayInputStream generarPdf(Long id) {
        Optional<Factura> facturaOpt = facturaRepo.findById(id);

        if (facturaOpt.isEmpty()) {
            throw new IllegalArgumentException("Factura no encontrada con ID: " + id);
        }

        Factura factura = facturaOpt.get();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Contenido del PDF
            document.add(new Paragraph("Factura ID: " + factura.getIdFactura()));
            document.add(new Paragraph("Cliente: " + factura.getRazonSocial()));
            document.add(new Paragraph("RFC: " + factura.getRfcCliente()));
            document.add(new Paragraph("Dirección Fiscal: " + factura.getDireccionFiscal()));
            document.add(new Paragraph("Fecha de Emisión: " + factura.getFechaEmision()));

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
