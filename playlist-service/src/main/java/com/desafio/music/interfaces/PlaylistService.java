package com.desafio.music.interfaces;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.music.entity.Music;
import com.desafio.music.entity.Playlist;
import com.desafio.music.exception.BadRequestException;
import com.desafio.music.exception.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Interface REST responsável por gerenciar as requisições de playlists.
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
@Api("Interface REST responsável por gerenciar as requisições para as playlists")
public interface PlaylistService {

	/**
	 * Busca a playlist do usuario especificado.
	 * 
	 * @param user Usuario a ser pesquisado
	 * @return a playlist do usuario
	 * @throws BadRequestException quando ocorre algum problema na requisição
	 */
	@ApiOperation(value = "Visualiza a playlist do usuario.")
	@GetMapping(value="/playlists", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Playlist> getPlaylist(@RequestParam(value = "user", required = true) String user)
			throws BadRequestException;

	/**
	 * Atualiza uma playlist
	 * 
	 * @param playlistId Id da playlist a ser atualizada
	 * @param musics Lista de músicas
	 * @return A playlist atualizada
	 * @throws BadRequestException quando ocorre algum problema na requisição
	 * @throws NotFoundException   quando a playlist não existir
	 * 
	 */
	@ApiOperation(value = "Atualiza playlist.")	
	@PutMapping(value="/playlists/{playlistId}/musicas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Playlist> updatePlaylist(@PathVariable("playlistId") String playlistId,
			@RequestBody Set<Music> musics) throws BadRequestException, NotFoundException;

	/**
	 * Remove uma música da playlist.
	 * 
	 * @param playlistId Id da playlist a ser atualizada
	 * @param musicId    ID da música a ser removida
	 * @throws NotFoundException   quando a musica não existir
	 * @throws BadRequestException quando ocorre algum problema na requisição
	 */
	@ApiOperation(value = "Remove uma música da playlist.")
	@DeleteMapping(value="/playlists/{playlistId}/musicas/{musicaId}")
	void deleteMusic(@PathVariable("playlistId") String playlistId, @PathVariable("musicaId") String musicId)
			throws BadRequestException, NotFoundException;
}
