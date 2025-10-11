package com.financas.gestaofinanceira.exceptions.handler;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String message, String details) {}
