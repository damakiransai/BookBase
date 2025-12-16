package com.bookbase.controller;

import com.bookbase.DTO.BookDTO;
import com.bookbase.DTO.BookResponse;
import com.bookbase.entity.Book;
import com.bookbase.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<BookResponse> getAllBooks(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(bookService.getAllBooks());
    }

    @GetMapping("/getBook/{title}")
    public ResponseEntity<BookDTO> getBookByName(@PathVariable("title") String title){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(bookService.getBookByTitle(title));
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookDTO> addBook(@RequestBody Book book){
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(bookService.addBook(book));
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<BookDTO> updateBook(@RequestBody Book book,@PathVariable("id") long id){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(bookService.updateBook(book,id));
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("id") long id){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(bookService.deleteBook(id));
    }
}
