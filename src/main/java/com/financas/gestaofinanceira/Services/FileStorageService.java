package com.financas.gestaofinanceira.Services;

import com.financas.gestaofinanceira.configuration.FileStorageConfig;
import com.financas.gestaofinanceira.exceptions.FileStorageException;
import com.financas.gestaofinanceira.resources.FileController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            logger.info("Creating Directory: {}", fileStorageLocation);
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            logger.error("Could not create the directory where files will be stored!");
            throw new FileStorageException("Could not create the directory where files will be stored!", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if(fileName.contains("..")){
                throw new FileStorageException("Cannot store file outside current directory!");
            }
            logger.info("Saving file in Disk: {}", fileName);

            Path path = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception e){
            logger.error("Error while storing file in Disk!", e);
            throw new FileStorageException("Could not store file " + fileName + ". Please, try again! "+ e);
        }
    }
}
