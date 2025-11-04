package com.financas.gestaofinanceira.services.excel;

import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ReportExcelService {

    public void createExcel(String fileName, List<UserRequestDTO> dto) throws IOException {
        log.info("Creating excel {}", fileName);

        final String DESTINATION_PATH = "/home/igor/Excel Java/";
        String fullPath = DESTINATION_PATH + fileName + ".xlsx";

        try(var workbook = new XSSFWorkbook();
            var outputStream = new FileOutputStream(fullPath)){
            var spreadsheet = workbook.createSheet("Relatório");
            int numberOfRows = 0;
            addHeader(spreadsheet, numberOfRows);
            for (UserRequestDTO userRequestDTO : dto) {
                var row = spreadsheet.createRow(numberOfRows++);
                addCell(row, 0, userRequestDTO.getName());
                addCell(row, 1, userRequestDTO.getCpf());
                addCell(row, 2, userRequestDTO.getEmail());
                addCell(row, 3, userRequestDTO.getMonthlyIncome());
            }

            workbook.write(outputStream);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private void addCell(Row row, int column, String value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private void addCell(Row row, int column, Double value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private void addCell(Row row, int column, Integer value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private void addHeader(XSSFSheet spreadsheet, int numberOfRows) {
        var row = spreadsheet.createRow(numberOfRows);
        addCell(row, 0, "Nome");
        addCell(row, 1, "CPF");
        addCell(row, 2, "Email");
        addCell(row, 3, "Valor mensal");
    }
}
