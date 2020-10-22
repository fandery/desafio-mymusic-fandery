package com.desafio.music.interfaces;

import java.util.List;
import java.util.Optional;

import com.desafio.music.entity.Music;

/**
 * Interface responsável pelas regras de negócios de musicas.
 */
public interface MusicBusiness {

	/**
	 * Método responsável por buscar todos as musicas que tenha um nome ou artista
	 * especifico.
	 * 
	 * @param filter Nome da musica ou artista a ser filtrado
	 * @return Lista com as musicas filtradas.
	 */
	public List<Music> findAllByFilter(String filter);
	
	/**
	 * Método responsável por buscar uma musica 
	 * 
	 * @param id ID da musica a ser buscada 
	 * @return a musica.
	 */
	public Optional<Music> findById(String id);

}
