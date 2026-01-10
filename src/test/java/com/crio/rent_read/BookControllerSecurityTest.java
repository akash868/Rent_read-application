package com.crio.rent_read;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.crio.rent_read.config.SecurityConfig;
import com.crio.rent_read.controller.BookController;
import com.crio.rent_read.security.CustomUserDetailsService;
import com.crio.rent_read.service.BookService;

@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
class BookControllerSecurityTest {
    @Autowired MockMvc mvc;

    @MockBean
    BookService bookService;

    @MockBean
    CustomUserDetailsService service;

    @Test
    void userCannotCreateBook() throws Exception {
        mvc.perform(post("/books")
            .header("Authorization","Basic " + java.util.Base64.getEncoder().encodeToString("user@test.com:password".getBytes())))
            .andExpect(status().isUnauthorized()); // unless you mock user details, basic will be 401
    }
}
