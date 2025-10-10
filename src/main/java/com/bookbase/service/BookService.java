package com.bookbase.service;

import com.bookbase.entity.Book;
import com.bookbase.exception.APIException;
import com.bookbase.exception.ResourceNotFoundException;
import com.bookbase.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title){
        Book book=bookRepository.findByTitle(title);
        if(book==null){
            throw new ResourceNotFoundException("Book","title",title);
        }
        return book;
    }

    public String addBook(Book book){
        Book existingBook=bookRepository.findByTitle(book.getTitle());
        if(existingBook!=null){
            throw new APIException("book with title: "+book.getTitle()+" already exists");
        }
        bookRepository.save(book);
        return "Book Added Successfully";
    }

    public String deleteBook(Long id){
        Optional<Book> result=bookRepository.findById(id);
        if(result.isPresent()){
            Book book=result.get();
            bookRepository.delete(book);
            return "Book deleted Successfully";
        }
        throw new ResourceNotFoundException("Book","id",id);
    }



    public String updateBook(Book book,Long id){
        Optional<Book> result=bookRepository.findById(id);
        if(result.isPresent()){
            Book existingBook=result.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublicationYear(book.getPublicationYear());
            bookRepository.saveAndFlush(existingBook);
            return "Book updated Successfully";
        }
        throw new ResourceNotFoundException("Book","id",id);
    }


}
