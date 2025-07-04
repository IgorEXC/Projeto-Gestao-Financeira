package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.Services.FileStorageService;
import com.financas.gestaofinanceira.domain.dto.UploadFileResponseDTO;
import com.financas.gestaofinanceira.resources.docs.FileControllerDocs;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/file/v1")
public class FileController implements FileControllerDocs {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    @Override
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file){
        var fileName = fileStorageService.storeFile(file);
        var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/file/v1/dowloadFile")
                .path(fileName).toUriString();
        return new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @Override
    public List<UploadFileResponseDTO> uploadMultpartFiles(MultipartFile[] files){
        return List.of();
    }

    @Override
    public ResponseEntity<ResponseEntity> dowloadFile(String fileName, HttpServletRequest request) {
        return null;
    }
}
