package com.coding.Book_API.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class BookEntityDTO {

    private Integer id;
    private String name;
    private Integer yearOfPublication;
    private Integer price;

    // private List<Author> authors;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AuthorDTO> authorDTOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<AuthorDTO> getAuthorDTOList() {
        return authorDTOList;
    }

    public void setAuthorDTOList(List<AuthorDTO> authorDTOList) {
        this.authorDTOList = authorDTOList;
    }

    @Override
    public String toString() {
        return "BookEntityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", price=" + price +
                ", authorDTOList=" + authorDTOList +
                '}';
    }
}
