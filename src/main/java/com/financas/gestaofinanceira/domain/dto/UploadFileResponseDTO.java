package com.financas.gestaofinanceira.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UploadFileResponseDTO implements Serializable {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
