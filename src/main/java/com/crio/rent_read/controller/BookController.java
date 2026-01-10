package com.crio.rent_read.controller;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.rent_read.dto.BookDto;
import com.crio.rent_read.entity.Book;
import com.crio.rent_read.service.BookService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Books")
@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> list = bookService.getAll().stream().map(BookDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAvailable() {
        return ResponseEntity.ok(bookService.getAvailable().stream().map(BookDto::from).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody Book book) {
        Book saved = bookService.create(book);
        return ResponseEntity.status(201).body(BookDto.from(saved));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody Book book) {
        Book updated = bookService.update(id, book);
        return ResponseEntity.ok(BookDto.from(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
