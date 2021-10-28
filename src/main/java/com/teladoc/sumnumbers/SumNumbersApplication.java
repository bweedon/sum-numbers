package com.teladoc.sumnumbers;

import com.teladoc.sumnumbers.service.MathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SumNumbersApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SumNumbersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SumNumbersApplication.class, args);
	}

	@Override
	public void run(String... args) {
		logger.trace("Entering run method");
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print("Please enter number1: ");
			String num1 = in.nextLine();
			System.out.print("\nPlease enter number2: ");
			String num2 = in.nextLine();
			logger.debug(String.format("Entered %s as first number and %s as the second number", num1, num2));
			String sum = MathService.sum(num1, num2);
			System.out.printf("The sum is %s\n", sum);
		}
	}

}
