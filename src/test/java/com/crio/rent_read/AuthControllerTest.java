package com.crio.rent_read;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.crio.rent_read.config.SecurityConfig;
import com.crio.rent_read.controller.AuthController;
import com.crio.rent_read.entity.User;
import com.crio.rent_read.entity.User.Role;
import com.crio.rent_read.security.CustomUserDetailsService;
import com.crio.rent_read.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    AuthService authService;
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void signupCreatesUser() throws Exception{
       User u = new User();
       u.setId(3L); u.setEmail("a@b.com"); u.setFirstName("fn"); u.setLastName("ln"); u.setRole(Role.ADMIN); u.setPassword("password");
       when(authService.register(any())).thenReturn(u);

       mvc.perform(post("/auth/signup")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(u)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.email").value("a@b.com"));
    }

}
