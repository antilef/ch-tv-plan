package dev.antilef.chtvplan.config;

import dev.antilef.chtvplan.service.JWTService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationFilterTest {

    @Autowired
    private MockMvc mvc;

    private JWTService jwtService = new JWTService();

    @Test
    void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void shouldGenerateAuthToken() throws Exception {
        User u = new User("francisco","antilef", List.of());
        String jwtToken = jwtService.generateToken(u);

        assertNotNull(jwtToken);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/").header("Authorization", jwtToken))
                .andExpect(status().isOk());
    }


}