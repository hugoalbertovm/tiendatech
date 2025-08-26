package com.tiendatech.controller;

import com.tiendatech.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/articulos")
    public ResponseEntity<Resource> generarReporteArticulos(@RequestParam String tipo) throws IOException {
        Map<String, Object> parametros = new HashMap<>();
        return reporteService.generaReporte("articulo", parametros, tipo);
    }

    @GetMapping("/articulosFecha")
    public ResponseEntity<Resource> generarReportePorFecha(
            @RequestParam String tipo,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) throws IOException {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", java.sql.Date.valueOf(fechaInicio));
        parametros.put("fechaFin", java.sql.Date.valueOf(fechaFin));

        return reporteService.generaReporte("articulos_param", parametros, tipo);
    }

    @GetMapping("/categorias")
    public ResponseEntity<Resource> generarReporteCategorias(@RequestParam String tipo) throws IOException {
        Map<String, Object> parametros = new HashMap<>();
        return reporteService.generaReporte("categorias", parametros, tipo);
    }

}
