package com.gmail.jarmusik.kamil.DataRepoGithub.info.domain;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoRepo {

    private String fullName;
    private String description;
    private String cloneUrl;
    private Date createdAt;
    private int stars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoRepo infoRepo = (InfoRepo) o;
        return Objects.equals(getFullName(), infoRepo.getFullName()) &&
                Objects.equals(getCloneUrl(), infoRepo.getCloneUrl()) &&
                Objects.equals(getCreatedAt(), infoRepo.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getCloneUrl(), getCreatedAt());
    }
}
