package com.crio.rent_read.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import com.crio.rent_read.entity.Book;
import com.crio.rent_read.entity.Rental;
import com.crio.rent_read.entity.User;
import com.crio.rent_read.exception.ResourceNotFoundException;
import com.crio.rent_read.repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {
    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);
    private final RentalRepository rentalRepo;
    private final UserService userService; // small wrapper to fetch user
    private final BookService bookService;

    @Transactional
    public Rental rentBook(Long userId, Long bookId) {
        User user = userService.findById(userId);
        Book book = bookService.findById(bookId);

        long active = rentalRepo.countByUserAndReturnDateIsNull(user);
        if (active >= 2) {
            logger.warn("User {} tried to exceed rental limit", user.getEmail());
            throw new IllegalStateException("User has already reached maximum book rental limit!");
        }
        if (book.getAvailabilityStatus() != Book.AvailabilityStatus.AVAILABLE) {
            throw new IllegalStateException("Book is not available");
        }
        book.setAvailabilityStatus(Book.AvailabilityStatus.NOT_AVAILABLE);
        bookService.create(book); // save change

        Rental rental = Rental.builder()
                .user(user)
                .book(book)
                .rentedAt(LocalDate.now())
                .returnDate(null)
                .build();

        return rentalRepo.save(rental);
    }

    @Transactional
    public void returnBook(Long rentalId) {
        Rental rental = rentalRepo.findById(rentalId).orElseThrow(() -> new ResourceNotFoundException("Rental not found"));
        if (rental.getReturnDate() != null) return; // already returned
        rental.setReturnDate(LocalDate.now());
        Book b = rental.getBook();
        b.setAvailabilityStatus(Book.AvailabilityStatus.AVAILABLE);
        bookService.create(b);
        rentalRepo.save(rental);
    }

    public List<Rental> activeRentalsForUser(Long userId) {
        User user = userService.findById(userId);
        return rentalRepo.findByUserAndReturnDateIsNull(user);
    }
}