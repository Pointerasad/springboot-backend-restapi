package com.springboot.restapi.repository;

import com.springboot.restapi.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by User on 3/23/2022.
 */

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress,Integer>{

   // Optional<UserProgress> findByUser(Integer userId);

    List<UserProgress> findTop10ByOrderByScoreDesc();

    Optional<UserProgress> findByUser_Id(Integer userId);
}
