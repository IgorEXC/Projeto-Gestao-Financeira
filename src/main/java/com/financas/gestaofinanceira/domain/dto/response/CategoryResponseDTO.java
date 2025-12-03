package com.financas.gestaofinanceira.domain.dto.response;


import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {

    private Long id;

    @Size(max = 40)
    private String name;
}
