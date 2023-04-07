package com.coding.Book_API.controller;


import com.coding.Book_API.Service.AuthorService;
import com.coding.Book_API.common.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {


    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/authors")
    private APIResponse getAuthorsList(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        /*  APIResponse apiResponse = new APIResponse();*/

        APIResponse apiResponse = authorService.getAuthors(pageable);
        return apiResponse;
    }

    //Sorting in Named Query

    @GetMapping(value = "/authorsWithNamed")
    private APIResponse getAuthorsWithNamedQuery(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        /*  APIResponse apiResponse = new APIResponse();*/

        APIResponse apiResponse = authorService.getAuthorsWithNamedQuery(pageable);
        return apiResponse;
    }
}
