package org.adex;

import org.adex.models.Book;
import org.adex.repositories.IBookRepository;
import org.adex.services.IBookServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAopApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(IBookRepository bookRepository, IBookServices bookServices){
        return args -> {
            bookRepository.deleteAll();
            bookServices.saveNewBook(Book.builder().title("java 101").price(120).quantity(20).build());
            bookServices.saveNewBook(Book.builder().title("React for All").price(100).quantity(10).build());
            // Exception price > 0
            bookServices.saveNewBook(Book.builder().title("Effective java Book").price(0).quantity(21).build());
        };
    }
}
