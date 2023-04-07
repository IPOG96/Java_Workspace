package com.coding.Book_API.validator;

import com.coding.Book_API.common.Error;
import com.coding.Book_API.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookValidator {
    public List<Error> validateCreateBookRequest(Book book) {

        List<Error> errors = new ArrayList<>();
        if (book.getName() == null) {
            Error error = new Error("name", "Book name should not be null");
            errors.add(error);

        }
        if (book.getYearOfPublication() == null) {
            new Error("YearOfPublication", "YearOfPublication is null");
            errors.add(new Error("YearOfPublication", "YearOfPublication is null"));
        }

        return errors;

    }
}
