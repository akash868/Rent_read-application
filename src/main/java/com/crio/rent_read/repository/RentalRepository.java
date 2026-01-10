package com.crio.rent_read.repository;

import java.util.List;
import com.crio.rent_read.entity.Rental;
import com.crio.rent_read.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>{
    List<Rental> findByUserAndReturnDateIsNull(User user); // active rentals
    long countByUserAndReturnDateIsNull(User user);
    List<Rental> findByUser(User user);
}
