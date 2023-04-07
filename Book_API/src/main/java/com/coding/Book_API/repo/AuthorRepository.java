package com.coding.Book_API.repo;

import com.coding.Book_API.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {


    Page<Author> findAllByOrderByIdDesc(Pageable pageable);
}
