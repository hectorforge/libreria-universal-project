package com.reporte.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generatePdfReport(String reportName, Map<String, Object> parameters, List<?> dataList) throws JRException, FileNotFoundException {
        // 1. Cargar el archivo .jrxml desde resources
        File file = ResourceUtils.getFile("classpath:reports/" + reportName + ".jrxml");

        // 2. Compilar el reporte (XML -> Jasper)
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // 3. Crear la fuente de datos
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        // 4. Llenar el reporte (Mezclar Plantilla + Datos + Parámetros)
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 5. Exportar a PDF (byte[])
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
