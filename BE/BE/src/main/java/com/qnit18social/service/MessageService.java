package com.qnit18social.service;

import com.qnit18social.models.Message;
import com.qnit18social.models.User;

import java.util.List;

public interface MessageService {

    Message createMessage(User user, Integer chatId, Message req) throws Exception;
    List<Message> findChatsMessages(Integer chatId) throws Exception;
}
