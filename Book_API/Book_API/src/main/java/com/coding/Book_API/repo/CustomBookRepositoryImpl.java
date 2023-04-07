package com.coding.Book_API.repo;

import com.coding.Book_API.dto.BookQueryDslDTO;
import com.coding.Book_API.entity.Book;
import com.coding.Book_API.entity.QBook;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class CustomBookRepositoryImpl implements CustomBookRepository {


    @PersistenceContext
    EntityManager entityManager;

    public static QBook qBook = QBook.book;

    @Override
    public List<Book> queryDslList(Integer year) {


        JPAQuery<Book> jpaQuery = new JPAQuery<>(entityManager);


       /* List<Book> bookList= jpaQuery
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();*/


        //Method 1 : Using Tuples
       /* List<Tuple> tuples= jpaQuery.select(qBook.id,qBook.name)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        List<Book> books=new ArrayList<>();


        for (Tuple eachTuple: tuples) {

            Book book=new Book();
            book.setId(eachTuple.get(qBook.id));
            book.setName(eachTuple.get(qBook.name));
            books.add(book);
        }

        return books;*/

        // return bookList;


        //Method 2 : Using projections


        QBean<Book> qBean = Projections.bean(Book.class, qBook.id, qBook.name);


        List<Book> bookList = jpaQuery
                .select(qBean)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        return bookList;


    }

    @Override
    public List<BookQueryDslDTO> queryDslListDto(Integer year) {

        QBean<BookQueryDslDTO> dslDTOQBean = Projections.bean(BookQueryDslDTO.class, qBook.id, qBook.name.as("bookname"));

        JPAQuery<BookQueryDslDTO> jpaQuery = new JPAQuery<>(entityManager);


        List<BookQueryDslDTO> bookQueryDslDTOList = jpaQuery
                .select(dslDTOQBean)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        return bookQueryDslDTOList;
    }
}
