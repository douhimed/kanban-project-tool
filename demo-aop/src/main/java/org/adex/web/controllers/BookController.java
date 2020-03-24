package org.adex.web.controllers;

import org.adex.models.Book;
import org.adex.services.IBookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookServices bookServices;

    @GetMapping
    public List<Book> index(){
        return this.bookServices.findAllBooks();
    }
}
