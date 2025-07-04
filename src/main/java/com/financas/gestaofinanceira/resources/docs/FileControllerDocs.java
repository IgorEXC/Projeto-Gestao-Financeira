package com.financas.gestaofinanceira.resources.docs;

import com.financas.gestaofinanceira.domain.dto.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "File Endpoint")
public interface FileControllerDocs {

    UploadFileResponseDTO uploadFile(MultipartFile file);
    List<UploadFileResponseDTO> uploadMultpartFiles(MultipartFile[] files);
    ResponseEntity<ResponseEntity> dowloadFile(String fileName, HttpServletRequest request);
}
