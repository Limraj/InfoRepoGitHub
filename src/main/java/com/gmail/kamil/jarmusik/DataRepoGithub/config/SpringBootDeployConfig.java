package com.gmail.kamil.jarmusik.DataRepoGithub.config;

import com.gmail.kamil.jarmusik.DataRepoGithub.DataRepoGithubApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class SpringBootDeployConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DataRepoGithubApplication.class);
    }
}
