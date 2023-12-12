package com.qnit18social.service;

import com.qnit18social.models.Chat;
import com.qnit18social.models.User;

import java.util.List;

public interface ChatService {

    Chat createChat(User reqUser, User user2);

    Chat findChatById(Integer chatId) throws Exception;

    List<Chat> findUsersChat(Integer userId);

}
