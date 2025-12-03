package com.financas.gestaofinanceira.domain.dto.request;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private Long id;
    private String name;
}
