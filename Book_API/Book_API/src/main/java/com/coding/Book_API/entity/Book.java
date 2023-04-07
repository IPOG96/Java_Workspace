package com.coding.Book_API.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_list")
public class Book {

    @Id
    @GeneratedValue(generator = "book", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book", sequenceName = "book_seq1", initialValue = 1, allocationSize = 1)
    /*   @GeneratedValue(strategy = GenerationType.AUTO)*/
    private Integer id;
    //	@Column(name = "name")
    private String name;


    private Integer yearOfPublication;
    private Integer price;

    public Book() {

    }


    public Book(Integer id, String name, Integer yearOfPublication, Integer price) {
        this.id = id;
        this.name = name;
        this.yearOfPublication = yearOfPublication;
        this.price = price;
    }

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

}
