package com.crio.rent_read.service;

import lombok.RequiredArgsConstructor;
import com.crio.rent_read.entity.User;
import com.crio.rent_read.exception.ResourceNotFoundException;
import com.crio.rent_read.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    public User findById(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")); }
}
