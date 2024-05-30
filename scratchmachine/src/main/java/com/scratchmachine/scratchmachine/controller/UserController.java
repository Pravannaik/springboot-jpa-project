package com.scratchmachine.scratchmachine.controller;

import com.scratchmachine.scratchmachine.dto.request.UserRequest;
import com.scratchmachine.scratchmachine.dto.response.Response;
import com.scratchmachine.scratchmachine.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-user-data")
    public ResponseEntity<Response> addUserData(@RequestBody @Valid UserRequest request) {
        userService.addUserData(request);

        Response response = new Response();
        response.setMessage("User data has been added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
