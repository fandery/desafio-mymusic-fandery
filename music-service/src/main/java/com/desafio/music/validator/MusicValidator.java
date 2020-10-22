package com.desafio.music.validator;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.desafio.music.exception.BadRequestException;

/**
 * Classe responsável por validar os campos de musica.
 */
@Component
@Scope(value = SCOPE_SINGLETON)
public class MusicValidator {

    private final Logger logger = LogManager.getLogger();

    /**
     * Construtor default.
     */
    public MusicValidator() {
        super();
    }

    public void validateSearch(String search) throws BadRequestException {
        if (search.length() < 3) {
            logger.error("Numero de caracteres da busca menor que 3.");
            throw new BadRequestException("Numero de caracteres da busca deve ser maior ou igual a 3.");
        }        
    }

}
