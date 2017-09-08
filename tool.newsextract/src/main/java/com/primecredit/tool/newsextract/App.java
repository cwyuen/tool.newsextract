package com.primecredit.tool.newsextract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableScheduling
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.debug("News Extract - Start");

		SpringApplication.run(App.class, args);

	}

}
