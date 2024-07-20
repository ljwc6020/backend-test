package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.domain.Users;
import kr.co.polycube.backendtest.dto.LottoDto;

public interface LottoService {
     LottoDto generateLottoNumbers(Long id);
}
