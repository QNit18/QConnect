package com.qnit18social.service;

import com.qnit18social.exception.UserException;
import com.qnit18social.models.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User findUserById(Integer userId) throws UserException;

    User findUserByEmail(String email);

    User followUser(Integer userId1, Integer userId2) throws UserException;

    List<User> searchUser(String query);

    User updateUser(User user, Integer userId) throws UserException;

    User findUserByJwt(String jwt);
}
