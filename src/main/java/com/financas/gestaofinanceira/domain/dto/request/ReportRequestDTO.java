package com.financas.gestaofinanceira.domain.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDTO {

    private Long id;

    private Long userId;
}
