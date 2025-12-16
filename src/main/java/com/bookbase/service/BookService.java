package com.bookbase.service;

import com.bookbase.DTO.BookDTO;
import com.bookbase.DTO.BookResponse;
import com.bookbase.entity.Book;
import com.bookbase.exception.APIException;
import com.bookbase.exception.ResourceNotFoundException;
import com.bookbase.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    public BookResponse getAllBooks(){
        java.util.List<Book> books =bookRepository.findAll();
        List<BookDTO> bookDTOS=books.stream().map(book -> modelMapper.map(book, BookDTO.class)).toList();

        BookResponse bookResponse=new BookResponse(bookDTOS);
        return bookResponse;
    }

    public BookDTO getBookByTitle(String title){
        Book book=bookRepository.findByTitle(title);
        if(book==null){
            throw new ResourceNotFoundException("Book","title",title);
        }
        return modelMapper.map(book, BookDTO.class);
    }

    public BookDTO addBook(Book book){
        Book existingBook=bookRepository.findByTitle(book.getTitle());
        if(existingBook!=null){
            throw new APIException("book with title: "+book.getTitle()+" already exists");
        }
        BookDTO bookDTO=modelMapper.map(bookRepository.save(book), BookDTO.class);
        return bookDTO;
    }

    public BookDTO deleteBook(Long id){
        Optional<Book> result=bookRepository.findById(id);
        if(result.isPresent()){
            Book book=result.get();
            bookRepository.delete(book);
            return modelMapper.map(book,BookDTO.class);
        }
        throw new ResourceNotFoundException("Book","id",id);
    }



    public BookDTO updateBook(Book book,Long id){
        Optional<Book> result=bookRepository.findById(id);
        if(result.isPresent()){
            Book existingBook=result.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublicationYear(book.getPublicationYear());
            BookDTO bookDTO=modelMapper.map(bookRepository.saveAndFlush(existingBook), BookDTO.class);
            return bookDTO;
        }
        throw new ResourceNotFoundException("Book","id",id);
    }


}
