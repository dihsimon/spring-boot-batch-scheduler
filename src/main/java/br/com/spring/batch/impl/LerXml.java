package br.com.spring.batch.impl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;

/**
 * Interface que contem os steps de um JOB
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Carmona</a>
 * 6 de jun de 2018
 */
public interface LerXml {

	@Bean
	public StaxEventItemReader<?> reader();
	
	@Bean
	public <T> T processor();
	
	@Bean
	public JdbcBatchItemWriter<?> writer();
	
	@Bean
	public Step step1();
	
	@Bean
	public Job importUserJob();
}
