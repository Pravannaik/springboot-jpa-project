package com.scratchmachine.scratchmachine.service;

import com.scratchmachine.scratchmachine.dto.request.UserRequest;

public interface UserService {
    void addUserData(UserRequest request);

    void loadUserData();

    void deleteAll();
}
