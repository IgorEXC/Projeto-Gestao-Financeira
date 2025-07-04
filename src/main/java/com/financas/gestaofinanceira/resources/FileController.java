package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.Services.FileStorageService;
import com.financas.gestaofinanceira.domain.dto.UploadFileResponseDTO;
import com.financas.gestaofinanceira.resources.docs.FileControllerDocs;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/file/v1")
public class FileController implements FileControllerDocs {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;

    @Override
    public UploadFileResponseDTO uploadFile(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public List<UploadFileResponseDTO> uploadMultpartFiles(MultipartFile[] files) throws IOException {
        return List.of();
    }

    @Override
    public ResponseEntity<ResponseEntity> dowloadFile(String fileName, HttpServletRequest request) throws IOException {
        return null;
    }
}
