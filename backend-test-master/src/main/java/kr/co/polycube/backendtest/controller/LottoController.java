package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.domain.Users;
import kr.co.polycube.backendtest.dto.LottoDto;
import kr.co.polycube.backendtest.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LottoController {

    private final LottoService lottoService;

    @PostMapping("/lottos")
    public ResponseEntity<LottoDto> generateLottoNumbers(@RequestBody Long id) {
        LottoDto responseDto = lottoService.generateLottoNumbers(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
