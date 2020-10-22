package com.desafio.music.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.music.entity.Music;

/**
 * Interface responsável pela interação com a tabela MUSICAS no banco de dados.
 */
@Repository
public interface MusicDAO extends JpaRepository<Music, String> {

	/**
	 * Consulta as musicas utilizando o filtro com o valor do nome da musica ou
	 * artista.
	 * 
	 * @param nome - nome da musica ou artista para ser utilizado no filtro
	 * @return Página com a lista de musicas de acordo com filtro
	 */	
	List<Music> findByNomeContainingIgnoreCaseOrderByArtistaNomeDescNomeDesc(String nome);

}