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

    @GetMapping("/carrito")
    public ResponseEntity<Resource> carrito(@RequestParam(defaultValue = "Pdf") String tipo) throws IOException {
        Map<String, Object> params = new HashMap<>();
        return reporteService.generaReporte("carrito", params, tipo);
    }
}
