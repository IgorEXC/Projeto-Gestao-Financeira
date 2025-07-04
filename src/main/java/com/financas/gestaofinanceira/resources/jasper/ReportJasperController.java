package com.financas.gestaofinanceira.resources.jasper;

import com.financas.gestaofinanceira.Services.jasper.ReportJasperService;
import com.financas.gestaofinanceira.domain.dto.jasper.ReportJasperDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/report-pdf")
public class ReportJasperController {

    private final ReportJasperService service;

    @PostMapping("/generate-report")
    public void generateReportPdf(@Valid @RequestBody ReportJasperDTO dto) throws IOException {
        service.generateReportPdf(dto);
    }
}
