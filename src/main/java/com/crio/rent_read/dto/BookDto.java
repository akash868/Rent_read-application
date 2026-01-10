package com.crio.rent_read.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.crio.rent_read.entity.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private Book.Genre genre;
    private Book.AvailabilityStatus availabilityStatus;

    public static BookDto from(Book b) {
        return new BookDto(b.getId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getAvailabilityStatus());
    }
}
