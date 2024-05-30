package com.scratchmachine.scratchmachine.service.impl;

import com.scratchmachine.scratchmachine.dto.request.UserRequest;
import com.scratchmachine.scratchmachine.entity.Users;
import com.scratchmachine.scratchmachine.repository.UserRepository;
import com.scratchmachine.scratchmachine.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void addUserData(UserRequest request) {
        userRepository.save(new Users(request.getUserId(), request.getFirstName(), request.getLastName(),
                request.isActive(), ""));
    }

    @Override
    public void loadUserData() {

        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users(18901L, "Pravan", "Naik", true, ""));
        usersList.add(new Users(18902L, "Tony", "Stark", true, ""));
        usersList.add(new Users(18903L, "Bruce", "Banner", true, ""));
        usersList.add(new Users(18904L, "Chris", "Evans", true, ""));
        usersList.add(new Users(18905L, "Natasha", "Romanoff", true, ""));

        userRepository.saveAll(usersList);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
