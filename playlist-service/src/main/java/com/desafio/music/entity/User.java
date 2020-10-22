package com.desafio.music.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe responsável por modelar a tabela ARTISTA.
 */
@Entity
@Table(name = "usuarios")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "playlistId")
	private Playlist playlist;

	public User() {
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

	public User nome(String nome) {
		this.nome = nome;
		return this;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nome=" + nome + "]";
	}

}
