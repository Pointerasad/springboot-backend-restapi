package com.springboot.restapi.repository;

import com.springboot.restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by User on 3/22/2022.
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
