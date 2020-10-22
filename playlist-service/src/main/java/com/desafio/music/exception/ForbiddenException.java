package com.desafio.music.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
* Exceção lançada quando usuário não tem acesso a determinado serviço.
 */
@ResponseStatus(value = FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 6193826253660952204L;

    public ForbiddenException() {
		super();
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForbiddenException(String message) {
		super(message);
	}

	public ForbiddenException(Throwable cause) {
		super(cause);
	}
}