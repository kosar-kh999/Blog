package com.example.blog.service;

import com.example.blog.model.User;
import com.example.blog.model.dto.LoginUserDto;
import com.example.blog.model.dto.UserDto;
import com.example.blog.repository.UserRepository;
import com.example.blog.util.exception.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    public void signUp(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    public UserDto login(LoginUserDto loginUserDto) {
        User user = userRepository.findUserByUsername(loginUserDto.getUsername());
        if (user == null) throw new BusinessException("کاربری با این مشخصات پیدا نشد");
        else return modelMapper.map(user, UserDto.class);
    }
}
