package br.com.spring.batch.schedulers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Classe que mapeia o tempo para execução do JOB
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Carmona</a>
 * 6 de jun de 2018
 */
@Component
@EnableScheduling
public class MyScheduler {

	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	public Job job;
	
	@Scheduled(cron="*/10 * * * * *")
	public void myScheduler() {
		
		JobParameters jobParameter = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		try {
			
			JobExecution execution = jobLauncher.run(job, jobParameter);
			System.out.println("#### STATUS: " + execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
