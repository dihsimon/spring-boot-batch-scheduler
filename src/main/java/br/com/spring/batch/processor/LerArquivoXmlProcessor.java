package br.com.spring.batch.processor;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import br.com.spring.batch.model.User;

public class LerArquivoXmlProcessor implements ItemProcessor<User, User>{

	@Override
	public User process(User user) throws Exception {
		user.setDateCreated(new Timestamp(new Date().getTime()));
		return user;
	}
}
