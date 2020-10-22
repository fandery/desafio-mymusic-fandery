package com.desafio.music.impl;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.music.entity.Music;
import com.desafio.music.exception.BadRequestException;
import com.desafio.music.interfaces.MusicBusiness;
import com.desafio.music.interfaces.MusicService;

/**
 * Classe responsável por implementações dos serviços definidos em
 * {@link MusicService}.
 */
@RestController
@Scope(value = SCOPE_SESSION)
public class MusicServiceImpl implements MusicService {

	private final Logger logger = LogManager.getLogger();

	private final MusicBusiness musicBusiness;

	/**
	 * Construtor com a injeção das dependências.
	 * 
	 * @param musicBusiness regras de negócio para os serviços de música
	 */
	public MusicServiceImpl(MusicBusiness musicBusiness) {
		super();
		this.musicBusiness = musicBusiness;
	}

	/**
	 * Método responsável por implementar {@link MusicService#getMusics(String)}.
	 */
	public ResponseEntity<List<Music>> getMusics(String filter) throws BadRequestException {
		logger.info("Requisição para listar musicas pelo filtro {}.", filter);
		HttpStatus status = HttpStatus.OK;
		List<Music> musics = musicBusiness.findAllByFilter(filter);
		if (musics.isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<List<Music>>(musics, status);
	}

	@Override
	public ResponseEntity<Music> getMusic(String id) throws BadRequestException {
		Optional<Music> music = musicBusiness.findById(id);
		return new ResponseEntity<Music>(music.get(), HttpStatus.OK);
	}

}
