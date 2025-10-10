package com.bookbase.controller;

import com.bookbase.entity.Book;
import com.bookbase.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/getBook/{title}")
    public Book getBookByName(@PathVariable("title") String title){
        return bookService.getBookByTitle(title);
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("/updateBook/{id}")
    public String updateBook(@RequestBody Book book,@PathVariable("id") long id){
        return bookService.updateBook(book,id);
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") long id){
        return bookService.deleteBook(id);
    }
}
