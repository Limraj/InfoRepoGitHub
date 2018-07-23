package com.gmail.kamil.jarmusik.DataRepoGithub.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DataRepo {

    private String fullName;
    private String description;
    private String cloneUrl;
    private Date createdAt;
    private int stars;

}
