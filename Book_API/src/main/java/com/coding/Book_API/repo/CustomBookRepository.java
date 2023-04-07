package com.coding.Book_API.repo;

import com.coding.Book_API.dto.BookQueryDslDTO;
import com.coding.Book_API.entity.Book;

import java.util.List;


public interface CustomBookRepository {
    List<Book> queryDslList(Integer year);

    List<BookQueryDslDTO> queryDslListDto(Integer year);

}
