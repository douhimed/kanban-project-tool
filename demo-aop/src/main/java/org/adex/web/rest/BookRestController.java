package org.adex.web.rest;

import org.adex.models.Book;
import org.adex.services.IBookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private IBookServices bookServices;

    @GetMapping
    public List<Book> index(){
        return this.bookServices.findAllBooks();
    }
}
