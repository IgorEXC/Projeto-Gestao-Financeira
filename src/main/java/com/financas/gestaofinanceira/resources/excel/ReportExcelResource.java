package com.financas.gestaofinanceira.resources.excel;

import com.financas.gestaofinanceira.Services.excel.ReportExcelService;
import com.financas.gestaofinanceira.domain.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/report-excel")
public class ReportExcelResource {

    private final ReportExcelService service;

    @PostMapping
    public ResponseEntity<Void> generateReportExcel(@RequestBody List<UserRequestDTO> dto) throws IOException {
        service.createExcel("Excel", dto);
        return ResponseEntity.ok().build();
    }
}
