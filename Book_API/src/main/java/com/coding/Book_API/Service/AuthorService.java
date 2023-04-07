package com.coding.Book_API.Service;


import com.coding.Book_API.common.APIResponse;
import com.coding.Book_API.common.PaginationMeta;
import com.coding.Book_API.data.AuthorData;
import com.coding.Book_API.entity.Author;
import com.coding.Book_API.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Autowired
    private AuthorData authorData;


    public APIResponse getAuthors(Pageable pageable) {

        APIResponse apiResponse = new APIResponse();

        Page<Author> authorPage = authorRepository.findAll(pageable);

        List<Author> authors = authorPage.getContent();


        PaginationMeta paginationMeta = PaginationMeta.createPagination(authorPage);


        authorData.setAuthorData(authors);
        authorData.setMeta(paginationMeta);

        // apiResponse.setData(authorPage);

        apiResponse.setData(authorData);

        return apiResponse;
    }

    public APIResponse getAuthorsWithNamedQuery(Pageable pageable) {

        APIResponse apiResponse = new APIResponse();

        Page<Author> authorPage = authorRepository.findAllByOrderByIdDesc(pageable);

        List<Author> authors = authorPage.getContent();


        PaginationMeta paginationMeta = PaginationMeta.createPagination(authorPage);


        authorData.setAuthorData(authors);
        authorData.setMeta(paginationMeta);

        // apiResponse.setData(authorPage);

        apiResponse.setData(authorData);

        return apiResponse;
    }
}
