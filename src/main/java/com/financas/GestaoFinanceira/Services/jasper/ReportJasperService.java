package com.financas.GestaoFinanceira.Services.jasper;

import com.financas.GestaoFinanceira.domain.dto.jasper.ReportJasperDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    private static final String IMAGELOGO_PATH = "classpath:jasper/reports/images/logo.png";

    //nome do arquivo, ou seja, o relatorio jasper
    private static final String REPORT_NAME = "relatorio_report.jrxml";

private static final String DESTINATION_PATH = "C://jasper-report//";

    public void generateReportPdf(ReportJasperDTO dto) throws IOException {

        byte[] imagebg = this.loadImage(IMAGEBG_PATH);
        byte[] imagelogo = this.loadImage(IMAGELOGO_PATH);

        Map<String, Object> params = new HashMap<>();
        params.put("id", dto.getId());
        params.put("name", dto.getName()); //tem que ser o mesmo nome dos parametros no Jasper
        params.put("expenseName", dto.getExpenseName());
        params.put("categoryName", dto.getName());
        params.put("expenseValue", dto.getExpenseValue());
        params.put("totalValue", dto.getTotalValue());

        String absolutePath = getAbsolutePath();
        try{
            String folderDiretory = getDirectorySave("reports-saved");
            JasperReport jasperReport = JasperCompileManager.compileReport(absolutePath);
            LOGGER.info("Report compiled successfully! {} " + absolutePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            LOGGER.info("Report filled successfully! {} " + absolutePath);
            JasperExportManager.exportReportToPdfFile(jasperPrint, absolutePath.replace(".jrxml", ".pdf"));
            LOGGER.info("Report exported successfully! {} " + absolutePath);
            LOGGER.info("Report generatjed successfully! {} " + absolutePath);
            JasperExportManager.exportReportToPdfFile(jasperPrint, folderDiretory);
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

    private String getDirectorySave(String filename) {
        this.createDirectory(DESTINATION_PATH);
        return DESTINATION_PATH + filename.concat("/") + filename + System.currentTimeMillis() + ".pdf";
    }

    private void createDirectory(String filename) {
        File directory = new File(filename);
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            LOGGER.info("Directory already exists!");
        }
    }

    private String getAbsolutePath() throws FileNotFoundException {
        return ResourceUtils.getFile(REPORT_PATH + REPORT_NAME).getAbsolutePath();
       // return REPORT_PATH + "/" + REPORT_NAME; - mesma coisa aparentemente
    }
}
