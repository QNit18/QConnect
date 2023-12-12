package com.qnit18social.controller;

import com.qnit18social.exception.UserException;
import com.qnit18social.models.User;
import com.qnit18social.reposity.UserRepository;
import com.qnit18social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId")Integer userId) throws UserException {
        return userService.findUserById(userId);
    }


    @PutMapping("/users")
    public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);

        return userService.updateUser(user, reqUser.getId());
    }

    @PutMapping("/users/follow/{userId2}")
    public User followUserHanlder(@RequestHeader("Authorization") String jwt,
                                  @PathVariable Integer userId2) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.followUser(reqUser.getId(), userId2);
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        return userService.searchUser(query);
    }

    @GetMapping("/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {

        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }

}
