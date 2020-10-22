package com.desafio.music.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Classe responsável por modelar a tabela ARTISTA.
 */
@Entity
@Table(name = "artistas")
public class Artist implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;
    
    @JsonIgnore
    @OneToMany(mappedBy="artista")
    private Set<Music> musics;

    public Artist() {
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

    public Artist nome(String nome) {
        this.nome = nome;
        return this;
    }
   
	public Set<Music> getMusics() {
		return musics;
	}

	public void setMusics(Set<Music> musics) {
		this.musics = musics;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nome=" + nome + "]";
	}

   }
