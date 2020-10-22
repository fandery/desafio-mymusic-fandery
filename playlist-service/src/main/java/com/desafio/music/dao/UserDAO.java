package com.desafio.music.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.music.entity.User;

/**
 * Interface responsável pela interação com a tabela USUARIOS no banco de dados.
 */
@Repository
public interface UserDAO extends JpaRepository<User, String> {

	/**
	 * Busca pelo usuario 
	 * 
	 * @param user - nome do usuario a ser buscado
	 * @return o usuario 
	 */	
	User findByNome(String nome);

}