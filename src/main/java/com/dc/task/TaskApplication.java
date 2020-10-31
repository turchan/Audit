package com.dc.task;

import com.dc.task.repository.AccountRepository;
import com.dc.task.repository.CustomerRepository;
import com.dc.task.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TaskApplication {

	@Autowired
	private static ApplicationContext context;

	@Component
	public static class ApplicationLifecycle implements Lifecycle {

		@Autowired
		private TransactionRepository transactionRepository;

		@Autowired
		private AccountRepository accountRepository;

		@Autowired
		private CustomerRepository customerRepository;

		@Override
		public void start() {
			log.info("Application start");
		}

		@Override
		public void stop() {
			log.info("Application stop");
			transactionRepository.deleteAll();
			accountRepository.deleteAll();
			customerRepository.deleteAll();
		}

		@Override
		public boolean isRunning() {
			return true;
		}
	}
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/");
		SpringApplication.run(TaskApplication.class, args);
	}

}
