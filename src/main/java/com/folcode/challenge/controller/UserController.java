package com.folcode.challenge.controller;

import com.folcode.challenge.dto.UserDTO;
import com.folcode.challenge.dto.UserParamDTO;
import com.folcode.challenge.dto.UserUpdateDTO;
import com.folcode.challenge.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody UserParamDTO userParamDTO, Errors errors) {

        if (errors.hasErrors()) {
            Map<String, Object> body = new HashMap<>();
            List<String> errosList = errors.getFieldErrors().stream().map(error -> "[" + error.getField() + "] " + error.getDefaultMessage()).collect(Collectors.toList());

            body.put("timestamp", LocalDateTime.now());
            body.put("status", 400);
            body.put("errors", errosList);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        userService.createUser(userParamDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable Long userId) {

        Optional<UserDTO> user = userService.findById(userId);

        if (user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        List<UserDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable Long userId) {
        try {
            userService.deleteById(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId, @Valid @RequestBody UserUpdateDTO userUpdateDTO, Errors errors) {

        if (errors.hasErrors()) {
            Map<String, Object> body = new HashMap<>();
            List<String> errosList = errors.getFieldErrors().stream().map(error -> "[" + error.getField() + "] " + error.getDefaultMessage()).collect(Collectors.toList());

            body.put("timestamp", LocalDateTime.now());
            body.put("status", 400);
            body.put("errors", errosList);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        userService.updateById(userId, userUpdateDTO);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
