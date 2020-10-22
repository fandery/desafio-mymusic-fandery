package com.desafio.music.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.music.entity.Playlist;

/**
 * Interface responsável pela interação com a tabela PLAYLISTS no banco de dados.
 */
@Repository
public interface PlaylistDAO extends JpaRepository<Playlist, String> {

	/**
	 * Consulta a playlist do usuario
	 * 
	 * @param user - nome do usuario a ser buscado
	 * @return Playlist do usuario 
	 */	
	Optional<Playlist> findById(String playlist);

}