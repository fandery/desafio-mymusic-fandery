package com.desafio.music.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe responsável por modelar a tabela PLAYLIST.
 */
@Entity
@Table(name = "playlists")
public class Playlist implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@OneToMany(mappedBy = "playlist")
	private Set<User> usuarios;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PlaylistMusicas", joinColumns = @JoinColumn(name = "playlistId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "musicaId", referencedColumnName = "id"))
	Set<Music> musics;

	public Playlist() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Music> getMusics() {
		return musics;
	}

	public void setMusics(Set<Music> musics) {
		this.musics = musics;
	}

}
