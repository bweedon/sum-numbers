package com.teladoc.sumnumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SumNumbersApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SumNumbersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SumNumbersApplication.class, args);
	}

	@Override
	public void run(String... args) {

	}

}
