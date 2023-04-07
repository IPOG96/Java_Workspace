package com.coding.Book_API.repo;

import com.coding.Book_API.entity.Book;
import com.coding.Book_API.entity.QBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>, CustomBookRepository {


    @PersistenceContext
    EntityManager entityManager = null;

    public static QBook qBook = QBook.book;

    // Updated to support named query

    /* List<Book> findAllByyearOfPublication(Integer yop); */

    // Updated to support Multiple query Param

    /* List<Book> findAllByyearOfPublicationIn(Set<Integer> yop); */

    // Updated to support multiple param

    List<Book> findAllByyearOfPublicationInAndPriceIn(Set<Integer> yop, Set<Integer> rate);

    //Updated for rawQuery

    //String rawQuery = "select * from book_list where year_of_publication in ?1";

    //  String rawQuery = "select * from book_list where year_of_publication IN ?1";

    String rawQuery = "select * from book_list where year_of_publication in :yop";

    @Query(nativeQuery = true, value = rawQuery)
    List<Book> getBooksByYop(@Param("yop") Set<Integer> yop);

    Book findAllById(Integer bookId);


}
