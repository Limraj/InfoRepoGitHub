package com.gmail.kamil.jarmusik.DataRepoGithub;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.SpringBootDeployConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataRepoGithubApplication extends SpringBootDeployConfig {

	public static void main(String[] args) {
		SpringApplication.run(DataRepoGithubApplication.class, args);
	}

}
