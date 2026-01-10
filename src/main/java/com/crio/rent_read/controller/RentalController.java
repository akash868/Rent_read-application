package com.crio.rent_read.controller;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.crio.rent_read.entity.Rental;
import com.crio.rent_read.service.RentalService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
@Tag(name = "Rentals")
@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/users/{userId}/books/{bookId}")
    public ResponseEntity<Rental> rentBook(@PathVariable Long userId, @PathVariable Long bookId) {
        Rental r = rentalService.rentBook(userId, bookId);
        return ResponseEntity.status(201).body(r);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/active-rentals/users/{userId}")
    public ResponseEntity<List<Rental>> activeForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(rentalService.activeRentalsForUser(userId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{rentalId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long rentalId) {
        rentalService.returnBook(rentalId);
        return ResponseEntity.noContent().build();
    }
}
