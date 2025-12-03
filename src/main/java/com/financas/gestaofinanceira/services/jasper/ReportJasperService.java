package com.financas.gestaofinanceira.services.jasper;

import com.financas.gestaofinanceira.domain.dto.jasper.ReportJasperDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReportJasperService {

    private static final Logger LOGGER = Logger.getLogger(ReportJasperService.class.getName());

    //caminho para o diretorio que contem os arquivos compilados do java. Classpath é o diretório resources do projeto java
    private static final String REPORT_PATH = "classpath:jasper/reports/";

    private static final String IMAGEBG_PATH = "classpath:jasper/reports/images/jasperbg.png";
    private static final String IMAGELOGO_PATH = "classpath:jasper/reports/images/logojasper.png";

    //nome do arquivo, ou seja, o relatorio jasper
    private static final String REPORT_NAME = "relatorio_report.jrxml";

    private static final String DESTINATION_PATH = "/home/igor/Relatorios Jasper/jasper-report/";
//os dados ja deve vim preenchido
    public void generateReportPdf(ReportJasperDTO dto) throws IOException {

        byte[] imagebg = this.loadImage(IMAGEBG_PATH);
        byte[] imagelogo = this.loadImage(IMAGELOGO_PATH);

        Map<String, Object> params = new HashMap<>();
        params.put("id", dto.getId());
        params.put("name", dto.getName()); //tem que ser o mesmo nome dos parametros no Jasper
        params.put("sumTotal", dto.getSumTotal());
        params.put("sumValue", dto.getSumValue());
        params.put("background", imagebg);
        params.put("logo", imagelogo);

        JRBeanCollectionDataSource listDataSource = new JRBeanCollectionDataSource(dto.getListExpense());
        params.put("listExpense", listDataSource);

        String absolutePath = getAbsolutePath();

        try{
            String folderDirectory = getDirectorySave("reports-saved/");
            JasperReport jasperReport = JasperCompileManager.compileReport(absolutePath);
            LOGGER.info("Report compiled successfully!");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            LOGGER.info("Report filled successfully! {} " + absolutePath);
            JasperExportManager.exportReportToPdfFile(jasperPrint, absolutePath.replace(".jrxml", ".pdf"));
            LOGGER.info("Report exported successfully!");
            LOGGER.info("Report generated successfully!");
            JasperExportManager.exportReportToPdfFile(jasperPrint, folderDirectory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] loadImage(String imagebgPath) throws IOException {
        String absolutePath = ResourceUtils.getFile(imagebgPath).getAbsolutePath();
        File file = new File(absolutePath);
        try (InputStream inputStream = new FileInputStream(file)){
            return IOUtils.toByteArray(inputStream);
        }     }

    private String getDirectorySave(String folderName) {
        String fullPath = DESTINATION_PATH + File.separator + folderName;
        this.createDirectory(fullPath);
        return fullPath + System.currentTimeMillis() + ".pdf";
    }

    private void createDirectory(String filename) {
        File directory = new File(filename);
        if (!directory.exists()) {
            directory.mkdirs();
        } else {
            LOGGER.info("Directory already exists!");
        }
    }

    private String getAbsolutePath() throws FileNotFoundException {
        return ResourceUtils.getFile(REPORT_PATH + REPORT_NAME).getAbsolutePath();
       // return REPORT_PATH + "/" + REPORT_NAME; - mesma coisa aparentemente
    }
}
