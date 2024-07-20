package kr.co.polycube.backendtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // moc 의존성 주입
    private UsersService usersService;

    @Test
    void testHandleValidationExceptions() throws Exception {
        // 잘못된 입력 테스트
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", ""); // 이름을 빈 문자열로

        // Perform the POST request to /test endpoint
        mockMvc.perform(MockMvcRequestBuilders.post("/users") // 포스트 요청
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()) // 400 오류인지 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name cannot be empty"))
                .andDo(print()); // 오류 출력
    }

    @Test
    public void testHandleNoHandlerFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/9999") // 없는 URL 요청
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound()) // 404 오류인지 확인
                .andDo(print());
    }


}
