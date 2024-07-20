package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.UsersDto;
import kr.co.polycube.backendtest.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoggingAspectTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @Test
    public void testLogClientAgent() throws Exception {
        UsersDto mockUser = new UsersDto();
        mockUser.setId(1L);
        mockUser.setName("Test User");

        when(usersService.registerUser(any(UsersDto.class))).thenReturn(mockUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test User\"}")
                        .header("User-Agent", "MockClientAgent"))
                .andExpect(status().isCreated());
    }
}
