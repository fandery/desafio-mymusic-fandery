package com.desafio.music.impl;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.desafio.music.dao.PlaylistDAO;
import com.desafio.music.dao.UserDAO;
import com.desafio.music.entity.Music;
import com.desafio.music.entity.Playlist;
import com.desafio.music.entity.User;
import com.desafio.music.exception.BadRequestException;
import com.desafio.music.exception.NotFoundException;
import com.desafio.music.interfaces.PlaylistBusiness;

/**
 * Classe responsável pela implementação de {@link PlaylistBusiness}.
 */
@Component
@Scope(value = SCOPE_SINGLETON)
public class PlaylistBusinessImpl implements PlaylistBusiness {
	private final Logger logger = LogManager.getLogger();

	private final UserDAO userDAO;
	private final PlaylistDAO playlistDAO;

	/**
	 * Construtor com a injeção das dependências..
	 * 
	 * @param userDAO
	 * @param playlistDAO
	 */
	public PlaylistBusinessImpl(UserDAO userDAO, PlaylistDAO playlistDAO) {
		super();
		this.userDAO = userDAO;
		this.playlistDAO = playlistDAO;
	}

	/**
	 * Método responsável por implementar
	 * {@link PlaylistBusiness#findByUser(String)}.
	 */
	@Override
	public Optional<Playlist> findByUser(String user) {
		User userFound = userDAO.findByNome(user);
		if (userFound == null) {
			logger.info("Usuário não encontrado");
			throw new NotFoundException("Usuário não encontado.");
		}

		Optional<Playlist> playlist = playlistDAO.findById(userFound.getPlaylist().getId());

		return playlist;

	}

	@Override
	public Optional<Playlist> findById(String id) {
		Optional<Playlist> playlist = playlistDAO.findById(id);
		if (!playlist.isPresent()) {
			logger.info("Playlist não encontrada");
			throw new BadRequestException("Playlist não encontada.");
		}

		return playlist;
	}

	@Override
	public Optional<Playlist> updatePlaylist(Playlist playlist, Set<Music> musics) {

		Set<Music> newMusics = new HashSet<Music>();
		for (Music music : musics) {
			newMusics.add(music);
		}

		playlist.setMusics(newMusics);

		Playlist playlistUpdated = playlistDAO.save(playlist);
		return Optional.of(playlistUpdated);
	}

	@Override
	public Music findByMusicaId(String id) {
		Map<String, String> variables = new HashMap<>();
		variables.put("id", id);

		ResponseEntity<Music> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/api/musicas/{id}",
				Music.class, variables);				
		
		return responseEntity.getBody();
	}

}
