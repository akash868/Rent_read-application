package com.crio.rent_read.repository;

import java.util.List;
import com.crio.rent_read.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByAvailabilityStatus(Book.AvailabilityStatus status);
}
