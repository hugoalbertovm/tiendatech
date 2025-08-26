package com.tiendatech.service.impl;

import com.tiendatech.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private DataSource dataSource;

    @Override
    public ResponseEntity<Resource> generaReporte(String reporte, Map<String, Object> parametros, String tipo) throws IOException {
        try {
            String estilo = ("vPdf".equals(tipo)) ? "inline; " : "attachment; ";
            String reportePath = "reportes";
            ByteArrayOutputStream salida = new ByteArrayOutputStream();

            ClassPathResource fuente = new ClassPathResource(
                    reportePath + File.separator + reporte + ".jasper");

            InputStream elReporte = fuente.getInputStream();

            JasperPrint reporteJasper = JasperFillManager.fillReport(
                    elReporte,
                    parametros,
                    dataSource.getConnection()
            );

            MediaType mediaType = null;
            String archivoSalida = "";
            byte[] data;

            switch (tipo) {
                case "Pdf", "vPdf" -> {
                    JasperExportManager.exportReportToPdfStream(reporteJasper, salida);
                    mediaType = MediaType.APPLICATION_PDF;
                    archivoSalida = reporte + ".pdf";
                }
                case "Xls" -> {
                    JRXlsxExporter exportador = new JRXlsxExporter();
                    exportador.setExporterInput(new SimpleExporterInput(reporteJasper));
                    exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(salida));
                    SimpleXlsxReportConfiguration configuracion = new SimpleXlsxReportConfiguration();
                    configuracion.setDetectCellType(true);
                    configuracion.setCollapseRowSpan(true);
                    exportador.setConfiguration(configuracion);
                    exportador.exportReport();
                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                    archivoSalida = reporte + ".xlsx";
                }
                case "Csv" -> {
                    JRCsvExporter exportador = new JRCsvExporter();
                    exportador.setExporterInput(new SimpleExporterInput(reporteJasper));
                    exportador.setExporterOutput(new SimpleWriterExporterOutput(salida));
                    exportador.exportReport();
                    mediaType = MediaType.TEXT_PLAIN;
                    archivoSalida = reporte + ".csv";
                }
                default -> {
                    JasperExportManager.exportReportToPdfStream(reporteJasper, salida);
                    mediaType = MediaType.APPLICATION_PDF;
                    archivoSalida = reporte + ".pdf";
                }
            }

            data = salida.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", estilo + "filename=\"" + archivoSalida + "\"");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(data.length)
                    .contentType(mediaType)
                    .body(new InputStreamResource(new ByteArrayInputStream(data)));

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
