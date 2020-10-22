package com.desafio.music.impl;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.music.entity.Music;
import com.desafio.music.entity.Playlist;
import com.desafio.music.exception.BadRequestException;
import com.desafio.music.exception.NotFoundException;
import com.desafio.music.interfaces.PlaylistBusiness;
import com.desafio.music.interfaces.PlaylistService;

/**
 * Classe responsável por implementações dos serviços definidos em
 * {@link PlaylistService}.
 */
@RestController
@Scope(value = SCOPE_SESSION)
public class PlaylistServiceImpl implements PlaylistService {

	private final Logger logger = LogManager.getLogger();


	private final PlaylistBusiness playlistBusiness;


	/**
	 * Construtor com a injeção das dependências.
	 * 
	 * @param playlistBusiness regras de negócio para os serviços de playlist
	 */
	public PlaylistServiceImpl(PlaylistBusiness playlistBusiness) {
		super();
		this.playlistBusiness = playlistBusiness;
	}

	/**
	 * Método responsável por implementar {@link MusicService#getMusics(String)}.
	 */

	@Override
	public ResponseEntity<Playlist> getPlaylist(String user) throws BadRequestException, NotFoundException {
		logger.info("Requisição para busca da playlist do usuario {}.", user);
		HttpStatus status = HttpStatus.OK;
		Optional<Playlist> playlist = playlistBusiness.findByUser(user);
		if (!playlist.isPresent()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<Playlist>(playlist.get(), status);
	}

	@Override	
	public ResponseEntity<Playlist> updatePlaylist(String playlistId, Set<Music> musics)
			throws BadRequestException, NotFoundException {
		Optional<Playlist> playlist  = playlistBusiness.findById(playlistId);		
		playlistBusiness.updatePlaylist(playlist.get(), musics);
		
		return new ResponseEntity<Playlist>(playlist.get(), HttpStatus.OK);
	}

	@Override	
	public void deleteMusic(String playlistId, String musicaId) throws BadRequestException, NotFoundException {
		Optional<Playlist> playlist  = playlistBusiness.findById(playlistId);		
		Music music = playlistBusiness.findByMusicaId(musicaId);			
		playlist.get().getMusics().remove(music);				
		playlistBusiness.updatePlaylist(playlist.get(), playlist.get().getMusics());		
	}

}
