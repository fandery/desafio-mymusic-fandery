package com.desafio.music.interfaces;

import java.util.Optional;
import java.util.Set;

import com.desafio.music.entity.Music;
import com.desafio.music.entity.Playlist;

/**
 * Interface responsável pelas regras de negócios de playlist.
 */
public interface PlaylistBusiness {

	/**
	 * Método responsável por buscar a playlist do usuario especificado
	 * 
	 * @param user Usuario a ser buscado 
	 * @return a playlist do usuario.
	 */
	public Optional<Playlist> findByUser(String user);
	
	/**
	 * Método responsável por buscar a playlist 
	 * 
	 * @param id ID da playlist a ser buscada 
	 * @return a playlist.
	 */
	public Optional<Playlist> findById(String id);
	
	/**
	 * Método responsável por adicionar as musicas na playlist 
	 * 
	 * @param playlist Playlist a ser atualizada
	 * @param musics Lista de musicas a serem adicionadas na playlist
	 * @return a playlist.
	 */
	public Optional<Playlist> updatePlaylist(Playlist playlist, Set<Music> musics);
	
	/**
	 * Método responsável por busca a musica pelo ID 
	 * 
	 * @param id ID da musica 
	 * @return a musica.
	 */
	public Music findByMusicaId(String id);

}
