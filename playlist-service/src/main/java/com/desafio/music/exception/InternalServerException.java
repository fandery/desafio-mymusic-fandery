package com.desafio.music.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção indicando um erro interno do servidor.
 */
@ResponseStatus(value = INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    private static final long serialVersionUID = 4845346791645973518L;

    public InternalServerException() {
        super();
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

}
