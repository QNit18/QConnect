package com.qnit18social.controller;

import com.qnit18social.models.Reels;
import com.qnit18social.models.User;
import com.qnit18social.service.ReelService;
import com.qnit18social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel,
                             @RequestHeader("Authorization")String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReels = reelService.createReels(reel, reqUser);

        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels(@RequestHeader("Authorization")String jwt) {
        List<Reels> reels= reelService.findAllReels();

        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable("userId")Integer userId)
            throws Exception {
        List<Reels> reels= reelService.findUsersReel(userId);
        return reels;
    }
}
