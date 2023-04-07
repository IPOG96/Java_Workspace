package com.coding.Book_API.repo;

import com.coding.Book_API.entity.BookAuthor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends CrudRepository<BookAuthor, Integer> {

    List<BookAuthor> findAllByBookId(Integer bookId);
}
