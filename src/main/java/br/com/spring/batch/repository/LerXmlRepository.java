package br.com.spring.batch.repository;

import org.springframework.stereotype.Repository;

/**
 * Repositorio para armanzenamento de comandos SQL
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Carmona</a>
 * 6 de jun de 2018
 */
@Repository
public interface LerXmlRepository {

	String INSERT_USER = new StringBuilder().append("INSERT INTO USER").append("(NAME, CREATED_DATA)").append("VALUES").append("(?,?)").toString();
}
