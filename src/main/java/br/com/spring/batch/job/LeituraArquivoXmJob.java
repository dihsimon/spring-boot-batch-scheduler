package br.com.spring.batch.job;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.xstream.XStreamMarshaller;
import br.com.spring.batch.impl.LerXml;
import br.com.spring.batch.model.User;
import br.com.spring.batch.processor.LerArquivoXmlProcessor;
import br.com.spring.batch.repository.LerXmlRepository;
import br.com.spring.batch.setter.LerXmlPreparedSetter;

/**
 * Job responsável por realizar a leitura de arquivos XML
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Carmona</a>
 * 6 de jun de 2018
 */
@Configuration
@EnableBatchProcessing
public class LeituraArquivoXmJob implements LerXml{

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public ResourceLoader resourceLoader;
	
	@Autowired
	public DataSource dataSource;

	@Bean
	public StaxEventItemReader<User> reader() {
		StaxEventItemReader<User> reader = new StaxEventItemReader<User>();
		
		Resource resource = resourceLoader.getResource("file:c:/xml/arquivo.xml");
		reader.setResource(resource);
		reader.setFragmentRootElementName("user");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "br.com.spring.batch.model.User");
		
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAliases(map);
		
		reader.setUnmarshaller(xStreamMarshaller);
		
		return reader;
	}

	@Bean
	public LerArquivoXmlProcessor processor() {
		return new LerArquivoXmlProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<User> writer() {
		JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<User>();
		writer.setDataSource(dataSource);
		writer.setSql(LerXmlRepository.INSERT_USER);
		writer.setItemPreparedStatementSetter(new LerXmlPreparedSetter());
		return null;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<User, User> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
}
