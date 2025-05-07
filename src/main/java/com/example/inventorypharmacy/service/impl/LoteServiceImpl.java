package com.example.inventorypharmacy.service.impl;



import com.example.inventorypharmacy.dto.LoteRequestDTO;
import com.example.inventorypharmacy.dto.LoteResponseDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository loteRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<LoteResponseDTO> listarLotes() {
        return loteRepo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public LoteResponseDTO registrarLote(LoteRequestDTO dto) {
        Producto producto = productoRepo.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Sucursal sucursal = sucursalRepo.findById(dto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Lote lote = new Lote();
        lote.setProducto(producto);
        lote.setCodigoLote(dto.getCodigoLote());
        lote.setFechaVencimiento(dto.getFechaVencimiento());
        lote.setCantidad(dto.getCantidad());
        lote.setFechaIngreso(dto.getFechaIngreso());
        lote.setPrecioUnitario(dto.getPrecioUnitario());
        lote.setNotas(dto.getNotas());
        lote.setSucursal(sucursal);

        Lote guardado = loteRepo.save(lote);
        return toDTO(guardado);
    }

    @Override
    public LoteResponseDTO actualizarLote(Long idLote, LoteRequestDTO dto) {
        Lote lote = loteRepo.findById(idLote)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        lote.setFechaVencimiento(dto.getFechaVencimiento());
        lote.setCantidad(dto.getCantidad());
        lote.setPrecioUnitario(dto.getPrecioUnitario());
        lote.setNotas(dto.getNotas());

        Lote actualizado = loteRepo.save(lote);
        return toDTO(actualizado);
    }

    @Override
    public void eliminarLote(Long idLote) {
        loteRepo.deleteById(idLote);
    }

    @Override
    public List<Map<String, Object>> obtenerStockPorSucursal(Long idSucursal) {
        List<Lote> lotes = loteRepo.findBySucursalIdSucursal(idSucursal);

        return lotes.stream()
                .collect(Collectors.groupingBy(
                        l -> l.getProducto(),
                        Collectors.summingInt(Lote::getCantidad)
                )).entrySet()
                .stream()
                .map(entry -> {
                    Producto producto = entry.getKey();
                    Map<String, Object> map = new HashMap<>();
                    map.put("idProducto", producto.getIdProducto());
                    map.put("nombre", producto.getNombre());
                    map.put("stock", entry.getValue());
                    map.put("unidad", producto.getUnidad().getDescripcion());
                    map.put("categoria", producto.getCategoria().getNombre());
                    return map;
                }).toList();
    }

    private LoteResponseDTO toDTO(Lote lote) {
        Producto p = lote.getProducto();
        return new LoteResponseDTO(
                lote.getIdLote(),
                p.getIdProducto(),
                p.getNombre(),
                p.getCategoria().getNombre(),
                p.getUnidad().getDescripcion(),
                p.getProveedor().getNombre(),
                lote.getCodigoLote(),
                lote.getFechaVencimiento(),
                lote.getCantidad(),
                lote.getFechaIngreso(),
                lote.getPrecioUnitario(),
                lote.getSucursal().getNombre(),
                lote.getNotas()
        );
    }
}
