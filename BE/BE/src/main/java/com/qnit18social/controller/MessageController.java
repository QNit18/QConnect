package com.qnit18social.controller;

import com.qnit18social.models.Message;
import com.qnit18social.models.User;
import com.qnit18social.service.MessageService;
import com.qnit18social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message reqMess,
                                 @PathVariable Integer chatId,
                                 @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Message message  = messageService.createMessage(user, chatId, reqMess);

        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatMessage(@PathVariable Integer chatId,
                                @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);

        List<Message> messageList  = messageService.findChatsMessages(chatId);

        return messageList;
    }
}
