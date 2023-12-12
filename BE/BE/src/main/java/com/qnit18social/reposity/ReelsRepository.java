package com.qnit18social.reposity;

import com.qnit18social.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {

    List<Reels> findByUserId(Integer userId);
}
