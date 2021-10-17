// This application will be loaded when gradlew.bat bootRun is called from command line after building the application.
package com.cjss.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}
	@Bean	// Bean has to return CommandLineRunner type
	public CommandLineRunner helloWorld(){
		return out->{
			System.out.println("Hello World");
		};
	}
	@Bean	// Bean not returning CommandLineRunner will not be invoked
	public void hello(){
		System.out.println("Not returning CommandLineRunner. Hence not invoked.");
	}
	@Bean 
	public CommandLineRunner hello2(){
		return out -> {
			System.out.println("Returning CommandLineRunner. Hence invoked.");
		};
	}
}
