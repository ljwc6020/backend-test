package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.domain.Users;
import kr.co.polycube.backendtest.dto.UsersDto;
import kr.co.polycube.backendtest.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {


    private final UsersRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UsersDto registerUser(UsersDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        Users registedUser = userRepository.save(user);
        return modelMapper.map(registedUser, UsersDto.class);
    }

    @Override
    public Optional<UsersDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UsersDto.class));
    }

    @Override
    public UsersDto updateUser(Long id, UsersDto userDto) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(userDto.getName());
                    Users updatedUser = userRepository.save(existingUser);
                    return modelMapper.map(updatedUser, UsersDto.class);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
