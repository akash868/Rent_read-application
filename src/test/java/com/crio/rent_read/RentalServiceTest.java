package com.crio.rent_read;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.Optional;
import com.crio.rent_read.entity.Book;
import com.crio.rent_read.entity.User;
import com.crio.rent_read.repository.RentalRepository;
import com.crio.rent_read.service.BookService;
import com.crio.rent_read.service.RentalService;
import com.crio.rent_read.service.UserService;

class RentalServiceTest {
    @Mock RentalRepository rentalRepo;
    @Mock UserService userService;
    @Mock BookService bookService;
    @InjectMocks RentalService rentalService;

    public RentalServiceTest(){ MockitoAnnotations.openMocks(this); }

    @Test
    void rentBook_whenTwoActive_thenThrow() {
        User user = new User(); user.setId(1L); user.setEmail("a@b.com");
        Book book = new Book(); book.setId(2L); book.setAvailabilityStatus(Book.AvailabilityStatus.AVAILABLE);

        when(userService.findById(1L)).thenReturn(user);
        when(bookService.findById(2L)).thenReturn(book);
        when(rentalRepo.countByUserAndReturnDateIsNull(user)).thenReturn(2L);

        try {
            rentalService.rentBook(1L,2L);
            throw new AssertionError("expected exception");
        } catch (IllegalStateException ex) {
            // expected
        }
    }
}
