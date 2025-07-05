package com.substring.irctc.service.impl;

import com.substring.irctc.dto.UserDto;
import com.substring.irctc.entity.Role;
import com.substring.irctc.entity.User;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.RoleRepo;
import com.substring.irctc.repositories.UserRepo;
import com.substring.irctc.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {


    private UserRepo userRepo;

    private RoleRepo roleRepo;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Role role = roleRepo.findByName("ROLE_NORMAL").orElseThrow(() -> new ResourceNotFoundException("Server is not configured properly, please contact support."));
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);


    }
}
