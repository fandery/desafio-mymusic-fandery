package com.desafio.music.interfaces;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.music.entity.Music;
import com.desafio.music.exception.BadRequestException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Interface REST responsável por gerenciar as requisições de músicas.
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
@Api("Interface REST responsável por gerenciar as requisições para as musicas")
public interface MusicService {

	/**
	 * Busca as musicas pelo nome da musica ou artista
	 * 
	 * @param filtro Filtro com o nome da musica ou artista
	 * @return Todos as musicas filtradas
	 */
	@ApiOperation(value = "Busca uma lista de musicas pelo nome ou artista.")
	@GetMapping(value = "/musicas", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Music>> getMusics(@RequestParam(value = "filter", required = false) String filter)
			throws BadRequestException;

	/**
	 * Busca a musica pelo ID
	 * 
	 * @param id ID da musica ou artista
	 * @return a musica especificada
	 */
	@ApiOperation(value = "Busca a musica pelo ID.")
	@GetMapping(value = "/musicas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Music> getMusic(@PathVariable("id") String id) throws BadRequestException;
}
