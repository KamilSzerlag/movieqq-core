package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.model.ResponseResult;
import com.moveqq.core.moveqqcore.model.dto.internal.User;
import com.moveqq.core.moveqqcore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:4200", maxAge = 3500)
@RestController
@RequestMapping(path = "/account")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/signin")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseResult> login(@RequestBody User user) {
        try {
            userService.checkUserCredentials(user);
            return new ResponseEntity<>(new ResponseResult(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            ResponseResult result = new ResponseResult();
            result.setResult(ResponseResult.ResultType.FAILED);
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
}
