package com.qnit18social.controller;

import com.qnit18social.models.Chat;
import com.qnit18social.models.User;
import com.qnit18social.request.CreateChatRequest;
import com.qnit18social.service.ChatService;
import com.qnit18social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestBody CreateChatRequest req,
                           @RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);

        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findChat(@RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        List<Chat> chatList = chatService.findUsersChat(reqUser.getId());
        return chatList;
    }
}
