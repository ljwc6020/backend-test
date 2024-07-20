package kr.co.polycube.backendtest.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class URLFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFilterWithValidURL() throws Exception {
        mockMvc.perform(get("/users/1?name=test"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFilterWithInvalidURL() throws Exception {
        mockMvc.perform(get("/users/1?name=test!!"))
                .andExpect(status().isBadRequest());
    }
}
