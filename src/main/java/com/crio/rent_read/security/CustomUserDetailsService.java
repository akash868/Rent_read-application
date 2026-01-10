package com.crio.rent_read.security;

import lombok.RequiredArgsConstructor;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import com.crio.rent_read.entity.User;
import com.crio.rent_read.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
        Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole().name())));
    }
    
}
