package com.coding.Book_API.Service;

import com.coding.Book_API.common.APIResponse;
import com.coding.Book_API.common.BadRequestException;
import com.coding.Book_API.common.Error;
import com.coding.Book_API.data.BookData;
import com.coding.Book_API.dto.AuthorDTO;
import com.coding.Book_API.dto.BookEntityDTO;
import com.coding.Book_API.dto.BookQueryDslDTO;
import com.coding.Book_API.entity.Author;
import com.coding.Book_API.entity.Book;
import com.coding.Book_API.entity.BookAuthor;
import com.coding.Book_API.repo.BookAuthorRepository;
import com.coding.Book_API.repo.BookRepository;
import com.coding.Book_API.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookService implements Serializable {

    // static List<Book> booklist = Arrays.asList(new Book(1, "Harry
    // Potter", 2012, 2000));

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookValidator bookValidator;


    /*
     * public List<Book> getBooks() {
     *
     * List<Book> booklist = new ArrayList<>(); //
     * bookRepository.findAll().forEach(books -> booklist.add(books));
     *
     * bookRepository.findAll().forEach(m -> booklist.add(m));
     *
     * return booklist; }
     */

    // Updated to support requestparam

    /*
     * public List<Book> getBooks(Integer yop) {
     *
     * List<Book> booklist = new ArrayList<>();
     *
     * if (yop == null) {
     *
     * bookRepository.findAll().forEach(m -> booklist.add(m)); return booklist;
     *
     * } else {
     *
     * // named query return bookRepository.findAllByyearOfPublication(yop); }
     *
     * }
     */

    // updated to support multiple query param

    public List<Book> getBooks(Set<Integer> yop, Set<Integer> rate) {

        List<Book> booklist = new ArrayList<>();

        if (yop == null) {

            bookRepository.findAll().forEach(booklist::add);
            /* bookRepository.findAll().forEach(m -> booklist.add(m));*/
            return booklist;

        } else {

            // named query
            return bookRepository.findAllByyearOfPublicationInAndPriceIn(yop, rate);
        }

    }

    public Book createBooks(Book book) {

        //validation
        List<Error> errors = bookValidator.validateCreateBookRequest(book);

        //if validation error occurs

        if (errors.size() > 0) {

            throw new BadRequestException("Bad Request", errors);
        }


        return bookRepository.save(book);
    }

    public BookEntityDTO getBooksById(Integer bookId, boolean authorData) {


        Book book;
        List<BookAuthor> bookAuthors = null;


        book = bookRepository.findAllById(bookId);
        if (authorData) {


            bookAuthors = bookAuthorRepository.findAllByBookId(bookId);

        }

        BookEntityDTO bookEntityDTO = new BookEntityDTO();

        //get Book details
        bookEntityDTO.setId(book.getId());
        bookEntityDTO.setName(book.getName());
        bookEntityDTO.setPrice(book.getPrice());
        bookEntityDTO.setYearOfPublication(book.getYearOfPublication());

        //set Author details
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        if (bookAuthors != null) {


            for (BookAuthor bookAuthor : bookAuthors) {
                Author author = new Author();
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(bookAuthor.getId());
                authorDTO.setName(bookAuthor.getAuthor().getName());
                authorDTO.setGender(bookAuthor.getAuthor().getGender());

                authorDTOList.add(authorDTO);

            }


            bookEntityDTO.setAuthorDTOList(authorDTOList);

        }

        return bookEntityDTO;


    }

    public Book updateBook(Book incomingBook) {

        // System.out.println("Message for incoming book" +incomingBook.toString());
        return bookRepository.save(incomingBook);
    }

    public String deleteBookById(Integer bookId) {

        bookRepository.deleteById(bookId);

        return "Deleted Successfully";
    }

    public APIResponse getBooksByRawQuery(Set<Integer> yop) {
        List<Book> bookList = bookRepository.getBooksByYop(yop);


      /*  Map map=new HashMap();
        map.put("book", bookList);*/


        APIResponse apiResponse = new APIResponse();
        //

        //TO GET AS AN OBJECT(book) USING MAP
        //  apiResponse.setData(map);


        BookData bookData = new BookData();

        //To get as an array
        bookData.setBooks(bookList);
        apiResponse.setData(bookData);
        // apiResponse.setError("No error");

        //To get as an object(not working)

        /*bookData.setBook((Book) bookList);
        apiResponse.setData(bookData);*/


        return apiResponse;
    }

    public APIResponse getExceptionMessage(Integer number) {

        int result = 100 / number;

        APIResponse response = new APIResponse();
        response.setData(result);
        return response;
    }

    public APIResponse getAllBookByQuerDsl(Integer year) {

        System.out.println("Inside sERVICE");
        APIResponse response = new APIResponse();

     //   List<Book> list = bookRepository.queryDslList(year);
        List<BookQueryDslDTO> bookQueryDslDTOS = bookRepository.queryDslListDto(year);
       // System.out.println("list " + list);
       // response.setData(list);
        response.setData(bookQueryDslDTOS);
        return response;
    }
}
