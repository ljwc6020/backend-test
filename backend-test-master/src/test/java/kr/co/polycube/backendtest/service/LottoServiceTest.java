package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.dto.LottoDto;

import kr.co.polycube.backendtest.repository.LottoRepository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Log4j2
public class LottoServiceTest {
    @Autowired
    private LottoService lottoService;
    @Autowired
    private  UsersService usersService;
    @Autowired
    private LottoRepository lottoRepository;

    @Test
    public void testGenerateLottoNumbers() {

       log.info(lottoService.generateLottoNumbers(1L));

    }
}
