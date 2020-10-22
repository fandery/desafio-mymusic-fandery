package com.desafio.music.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando ocorre conflito na requisição.
 */
@ResponseStatus(value = CONFLICT)
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflictException() {
		super();
	}

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException(Throwable cause) {
		super(cause);
	}
}