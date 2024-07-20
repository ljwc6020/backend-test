package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.dto.UsersDto;

import java.util.Optional;

public interface UsersService {
    UsersDto registerUser(UsersDto userDto);
    Optional<UsersDto> getUserById(Long id);
    UsersDto updateUser(Long id, UsersDto userDto);
}
