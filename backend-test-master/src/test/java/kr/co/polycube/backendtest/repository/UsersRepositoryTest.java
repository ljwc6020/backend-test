package kr.co.polycube.backendtest.repository;

import kr.co.polycube.backendtest.domain.Users;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
@Log4j2
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository userRepository;

    @Test
    public void testRegisterUser(){

        for (int i = 0; i < 10 ; i++) {
            Users user = Users.builder()
                    .name("user"+i)
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    public void testFindById() {

        Long id = 1L;

        Optional<Users> user = userRepository.findById(id);

        log.info(user);

    }





}
