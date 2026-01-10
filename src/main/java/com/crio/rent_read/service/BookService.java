package com.crio.rent_read.service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.crio.rent_read.entity.Book;
import com.crio.rent_read.exception.ResourceNotFoundException;
import com.crio.rent_read.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repo;

    public Book create(Book book) { return repo.save(book); }

    public Book update(Long id, Book book) {
        Book existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setGenre(book.getGenre());
        existing.setAvailabilityStatus(book.getAvailabilityStatus());
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Book not found");
        repo.deleteById(id);
    }

    public List<Book> getAll() { return repo.findAll(); }

    public List<Book> getAvailable() { return repo.findByAvailabilityStatus(Book.AvailabilityStatus.AVAILABLE); }

    public Book findById(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found")); }
}
