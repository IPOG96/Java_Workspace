package com.coding.Book_API.controller;

import com.coding.Book_API.Service.BookService;
import com.coding.Book_API.common.APIResponse;
import com.coding.Book_API.dto.BookEntityDTO;
import com.coding.Book_API.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class BookController {

    @Autowired
    private BookService bookservice;

    String test;

    /*
     * @RequestMapping(value = "/books") public List<Book> getbooks() {
     *
     * return bookservice.getBooks();
     *
     * }
     */

    // Updated to support requestparam in GET API

    /*
     * @RequestMapping(value = "/books")
     *
     * public List<Book> getbooks(@RequestParam(value = "yearOfPublication",
     * required = false) int yop) {
     *
     * return bookservice.getBooks(yop);
     *
     * }
     */

    // Updated to support Multiple Request Param

    @RequestMapping(value = "/books")

    public List<Book> getbooks
            (@RequestParam(value = "yearOfPublications", required = false) Set<Integer> yop,
             @RequestParam(value = "price", required = false) Set<Integer> rate) {

        return bookservice.getBooks(yop, rate);

    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Book createBooks(@RequestBody Book book) {

        return bookservice.createBooks(book);

    }

    @RequestMapping(value = "/books/{id}")
    public BookEntityDTO getBooksById(@PathVariable("id") Integer bookId,
                                      @RequestParam(value = "authorData", required = false) boolean authorData) {

        return bookservice.getBooksById(bookId, authorData);

    }

    @RequestMapping(value = "/books/", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book incomingBook) {
        return bookservice.updateBook(incomingBook);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public String deleteBookById(@PathVariable("id") Integer bookId) {
        return bookservice.deleteBookById(bookId);
    }

    @GetMapping(value = "/raw/books")
    public APIResponse getBooksByRawQuery(@RequestParam(value = "yop") Set<Integer> yop) {


        return bookservice.getBooksByRawQuery(yop);

    }

    @GetMapping(value = "getException")
    public APIResponse getExceptionMessage(@RequestParam(value = "number") Integer number) {


        return bookservice.getExceptionMessage(number);

    }

    @GetMapping(value = "queryDsl/books")
    public APIResponse getBooksByQueryDsl(@RequestParam("year") Integer year) {

        return bookservice.getAllBookByQuerDsl(year);

    }

}
