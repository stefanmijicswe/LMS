package org.singidunum.backend.dto;

import java.util.Date;
import java.util.List;

public class TeachingMaterialDTO {

    private Long id;
    private String name;
    private List<String> authors;
    private Date yearPublished;

    public TeachingMaterialDTO() {
    }

    public TeachingMaterialDTO(Long id, String name, List<String> authors, Date yearPublished) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.yearPublished = yearPublished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Date getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Date yearPublished) {
        this.yearPublished = yearPublished;
    }
}
