package com.example.Library.controller;


import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/library")
public class LibraryController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createbook")
    public ResponseEntity<Book> createbook(@RequestBody Book book){
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED );// inside the body
    }
    @GetMapping("/retrieveall")
    public ResponseEntity<List<Book>> retrieveall(){
        return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/retrievebyid/{bookid}")
    public ResponseEntity<Book> retrievebyid(@PathVariable Long bookid){
        Optional<Book> opt1= bookRepository.findById(bookid);
        if(opt1.isPresent()){
            return new ResponseEntity<Book>(opt1.get(), HttpStatus.ACCEPTED);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{bookid}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable Long bookid) {
        Optional<Book> opt2 = bookRepository.findById(bookid);
        Book bookop = null;
        if (opt2.isPresent()) {
            bookop = opt2.get();
            bookop.setBookName(book.getBookName());
            bookop.setAuthor(book.getAuthor());

            return new ResponseEntity<Book>(bookRepository.save(bookop), HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deletebyid/{bookid}")
    public ResponseEntity<String> delete(@PathVariable Long bookid){
        Optional<Book> opt3 = bookRepository.findById(bookid);
        if(opt3.isPresent()){
            bookRepository.deleteById(bookid);
            return new ResponseEntity<String>("deleted successfully", HttpStatus.FOUND);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
