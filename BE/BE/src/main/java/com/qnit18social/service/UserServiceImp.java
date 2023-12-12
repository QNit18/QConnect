package com.qnit18social.service;

import com.qnit18social.config.JwtProvider;
import com.qnit18social.exception.UserException;
import com.qnit18social.models.User;
import com.qnit18social.reposity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User register(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            return user.get();
        }
        throw new UserException("user not exist with userid: " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepository.searchUser(query);
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserException {
        Optional<User> user1 = userRepository.findById(userId);

        if(user1.isEmpty()){
            throw new UserException("User not exit with id: " + userId);
        }

        User oldUser = user1.get();
        if(user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender() != null) {
            oldUser.setGender(user.getGender());
        }

        return userRepository.save(oldUser);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);
        return user;
    }
}