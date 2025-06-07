package com.financas.GestaoFinanceira.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    public BusinessException(String message) {
        super(message);
    }
}
