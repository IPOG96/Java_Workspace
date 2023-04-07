package com.coding.Book_API.data;

import com.coding.Book_API.common.PaginationMeta;
import com.coding.Book_API.entity.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorData {


    private PaginationMeta meta;
    private List<Author> authorData;


    public PaginationMeta getMeta() {
        return meta;
    }

    public void setMeta(PaginationMeta meta) {
        this.meta = meta;
    }

    public List<Author> getAuthorData() {
        return authorData;
    }

    public void setAuthorData(List<Author> authorData) {
        this.authorData = authorData;
    }
}
