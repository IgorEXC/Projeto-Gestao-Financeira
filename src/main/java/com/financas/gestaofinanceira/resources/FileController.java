package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.services.FileStorageService;
import com.financas.gestaofinanceira.domain.dto.UploadFileResponseDTO;
import com.financas.gestaofinanceira.resources.docs.FileControllerDocs;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/file")
public class FileController implements FileControllerDocs {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    @Override //na requisição postman para upload de arquivos, tem que ser exatamente este nome definido dentro do @RequestParam
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file){
        var fileName = fileStorageService.storeFile(file);
        var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/file/v1/dowloadFile")
                .path(fileName).toUriString();
        return new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    @Override
    public List<UploadFileResponseDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
        return Arrays.stream(files)
                .map(this::uploadFile)
                .toList();
    }

    @GetMapping("/dowloadFile/{fileName:.+}")
    @Override
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex){
            logger.error("Could not determine file type! ", ex);
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
