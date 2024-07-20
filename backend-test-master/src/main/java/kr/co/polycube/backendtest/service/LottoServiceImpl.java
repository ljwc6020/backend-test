package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.domain.Lotto;
import kr.co.polycube.backendtest.domain.Users;
import kr.co.polycube.backendtest.dto.LottoDto;
import kr.co.polycube.backendtest.dto.UsersDto;
import kr.co.polycube.backendtest.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LottoServiceImpl implements LottoService {

    private final UsersService usersService;
    private final LottoRepository lottoRepository;
    private final ModelMapper modelMapper;

    @Override
    public LottoDto generateLottoNumbers(Long id) {
        Lotto lotto = new Lotto();

        Optional<UsersDto> userOptional = usersService.getUserById(id);

        if (userOptional.isPresent()) {
            UsersDto userDto = userOptional.get();

            Users user = modelMapper.map(userDto, Users.class);

            lotto.setUser(user);

            Random random = new Random();
            lotto.setNumber1(random.nextInt(45) + 1);
            lotto.setNumber2(random.nextInt(45) + 1);
            lotto.setNumber3(random.nextInt(45) + 1);
            lotto.setNumber4(random.nextInt(45) + 1);
            lotto.setNumber5(random.nextInt(45) + 1);
            lotto.setNumber6(random.nextInt(45) + 1);

            lottoRepository.save(lotto);

            return modelMapper.map(lotto, LottoDto.class);
        } else {
            throw new RuntimeException("User with " + id + " not found");
        }
    }
}
