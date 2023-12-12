package com.qnit18social.controller;

import com.qnit18social.config.JwtProvider;
import com.qnit18social.models.User;
import com.qnit18social.reposity.UserRepository;
import com.qnit18social.request.LoginRequest;
import com.qnit18social.response.AuthResponse;
import com.qnit18social.service.CustomerUserDetailsService;
import com.qnit18social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerUserDetailsService customerUserDetails;

    // auth/signup
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());

        if (isExist!=null){
            throw new Exception("==> Email already used with another account");
        }

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "==> Resgister successfully");

        return res;
    }

    // auth/sigin
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "==> Sign in success");

        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("==> Invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("==> Password not correct!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
