package com.desafio.music.impl;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.desafio.music.dao.MusicDAO;
import com.desafio.music.entity.Music;
import com.desafio.music.exception.BadRequestException;
import com.desafio.music.interfaces.MusicBusiness;
import com.desafio.music.validator.MusicValidator;
import com.google.common.base.Strings;

/**
 * Classe responsável pela implementação de {@link MusicBusiness}.
 */
@Component
@Scope(value = SCOPE_SINGLETON)
public class MusicBusinessImpl implements MusicBusiness {
	private final Logger logger = LogManager.getLogger();

	private final MusicDAO musicDAO;
	private final MusicValidator musicValidator;

	/**
	 * Construtor com a injeção das dependências..
	 * 
	 * @param musicaDAO
	 * @param musicaValidator
	 */
	public MusicBusinessImpl(MusicDAO musicDAO, MusicValidator musicValidator) {
		super();
		this.musicDAO = musicDAO;
		this.musicValidator = musicValidator;
	}

	/**
	 * Método responsável por implementar
	 * {@link MusicBusiness#findAllByFilter(Strings)}.
	 */
	@Override
	public List<Music> findAllByFilter(String filter) {
		musicValidator.validateSearch(filter);
		return musicDAO.findByNomeContainingIgnoreCaseOrderByArtistaNomeDescNomeDesc(filter);
	}

	@Override
	public Optional<Music> findById(String id) {
		Optional<Music> music = musicDAO.findById(id);
		if (!music.isPresent()) {
			logger.info("Música não encontrada");
			throw new BadRequestException("Música não encontada.");
		}

		return music;
	}

}
