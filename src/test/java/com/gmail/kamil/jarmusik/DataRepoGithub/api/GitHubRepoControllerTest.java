package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.DataRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(GitHubRepoController.class)
public class GitHubRepoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GitHubRepoPublicService service;

    @Test
    public void getDataRepo() {

        DataRepo dataRepo = service.get("ruby", "ruby");
/*
        mvc.perform(get("/repositories/ruby/ruby")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(alex.getName())));
    */}
}