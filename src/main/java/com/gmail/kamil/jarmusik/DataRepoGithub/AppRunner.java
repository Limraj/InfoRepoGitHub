package com.gmail.kamil.jarmusik.DataRepoGithub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AppRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class, args);
	}

}
