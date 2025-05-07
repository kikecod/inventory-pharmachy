package com.example.inventorypharmacy.service;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, Object> getResumenDashboard();
    List<Map<String, Object>> getVentasPorCategoria();
}
