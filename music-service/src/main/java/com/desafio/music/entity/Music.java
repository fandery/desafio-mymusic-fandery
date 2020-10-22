package com.desafio.music.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe responsável por modelar a tabela MUSICA.
 */
@Entity
@Table(name = "musicas")
public class Music implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "artistaId")
	private Artist artista;
		
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "musics")
	Set<Playlist> playlists;

	public Music() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Artist getArtista() {
		return artista;
	}

	public void setArtista(Artist artista) {
		this.artista = artista;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "Music [id=" + id + ", nome=" + nome + "]";
	}

}
