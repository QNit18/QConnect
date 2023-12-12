package com.qnit18social.service;

import com.qnit18social.models.Reels;
import com.qnit18social.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReelService {

    Reels createReels(Reels reel, User user);

    List<Reels> findAllReels();

     List<Reels> findUsersReel(Integer userId) throws Exception;
}
