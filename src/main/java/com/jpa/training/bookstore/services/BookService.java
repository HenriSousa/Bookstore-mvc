package com.jpa.training.bookstore.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jpa.training.bookstore.dtos.BookRecordDto;
import com.jpa.training.bookstore.models.BookModel;
import com.jpa.training.bookstore.models.ReviewModel;
import com.jpa.training.bookstore.repositories.AuthorRepository;
import com.jpa.training.bookstore.repositories.BookRepository;
import com.jpa.training.bookstore.repositories.PublisherRepository;

import jakarta.transaction.Transactional;


public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
            PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public BookModel saveBook(BookRecordDto bookRecordDto) {
        BookModel book = new BookModel();
        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
    
}
