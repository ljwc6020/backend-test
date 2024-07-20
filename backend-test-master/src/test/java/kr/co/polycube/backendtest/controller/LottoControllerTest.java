package kr.co.polycube.backendtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.dto.LottoDto;
import kr.co.polycube.backendtest.service.LottoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LottoController.class)
@AutoConfigureMockMvc
public class LottoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LottoService lottoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGenerateLottoNumbers() throws Exception {
        // Mock LottoService의 generateLottoNumbers 메서드 호출 시 리턴할 객체 설정
        LottoDto mockLottoDto = generateMockLottoDto();

        when(lottoService.generateLottoNumbers(any(Long.class))).thenReturn(mockLottoDto);

        // POST 요청 테스트
        Long userId = 1L;
        mockMvc.perform(post("/lottos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lottoId").exists())
                .andExpect(jsonPath("$.number1").exists())
                .andExpect(jsonPath("$.number2").exists())
                .andExpect(jsonPath("$.number3").exists())
                .andExpect(jsonPath("$.number4").exists())
                .andExpect(jsonPath("$.number5").exists())
                .andExpect(jsonPath("$.number6").exists());

        // 여기서의 jsonPath는 반환된 LottoDto 객체의 필드를 검증하는 예시입니다.
        // 실제 필드명에 따라 적절히 수정해주세요.
    }
    private LottoDto generateMockLottoDto() {
        Random random = new Random();
        LottoDto lottoDto = new LottoDto();
        lottoDto.setLottoId(1L);
        lottoDto.setNumber1(random.nextInt(45) + 1);
        lottoDto.setNumber2(random.nextInt(45) + 1);
        lottoDto.setNumber3(random.nextInt(45) + 1);
        lottoDto.setNumber4(random.nextInt(45) + 1);
        lottoDto.setNumber5(random.nextInt(45) + 1);
        lottoDto.setNumber6(random.nextInt(45) + 1);
        return lottoDto;
    }
}
