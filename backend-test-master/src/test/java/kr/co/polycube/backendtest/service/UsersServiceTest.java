package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.domain.Users;
import kr.co.polycube.backendtest.dto.UsersDto;
import kr.co.polycube.backendtest.repository.UsersRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testRegisterUser() {
        UsersDto userDto = new UsersDto(null, "NewUser");

        UsersDto registeredUser = usersService.registerUser(userDto);

        Assertions.assertNotNull(registeredUser.getId());
        Assertions.assertEquals(userDto.getName(), registeredUser.getName());
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;

        Optional<UsersDto> userDtoOptional = usersService.getUserById(userId);

        Assertions.assertTrue(userDtoOptional.isPresent());
        Assertions.assertEquals(userId, userDtoOptional.get().getId());
    }

    @Test
    public void testUpdateUser() {
        Long userId = 2L;
        UsersDto userDto = new UsersDto(null, "UpdatedUser");

        UsersDto updatedUser = usersService.updateUser(userId, userDto);

        Assertions.assertEquals(userId, updatedUser.getId());
        Assertions.assertEquals(userDto.getName(), updatedUser.getName());

        Optional<Users> optionalUser = usersRepository.findById(userId);
        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(userDto.getName(), optionalUser.get().getName());
    }

}
