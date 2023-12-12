package com.qnit18social.service;

import com.qnit18social.models.Story;
import com.qnit18social.models.User;

import java.util.List;

public interface StoryService {

    Story createStory(Story story, User user);

    List<Story> findStoryByUserId(Integer userId) throws Exception;
}
