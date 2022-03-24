package com.springboot.restapi.controller;


import com.springboot.restapi.dto.UserProgressDto;
import com.springboot.restapi.enums.MessageContainer;
import com.springboot.restapi.model.User;
import com.springboot.restapi.model.UserProgress;
import com.springboot.restapi.repository.UserProgressRepository;
import com.springboot.restapi.repository.UserRepository;
import com.springboot.restapi.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by User on 3/23/2022.
 */

@RestController
@RequestMapping("/api")
public class UserProgressController {

    @Autowired
    private UserProgressService userProgressService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    // This method is for add User progress
    @PostMapping("/save-user-progress-info")
    public ResponseEntity<?> addUserProgress(@RequestBody UserProgressDto userProgressDto) {

        Optional<User> isExistId = userRepository.findById(userProgressDto.getUserId());
        Optional<UserProgress> userProgressOpt = userProgressRepository.findByUser_Id(userProgressDto.getUserId());

       if(!userProgressOpt.isPresent()){
            if(userProgressDto.getUserId()!=null) {
                if (isExistId.isPresent()) {
                    userProgressService.saveOrUpdateUserProgressInfo(userProgressDto);
                    return new ResponseEntity<String>(MessageContainer.MESSAGE_SAVE_SUCCESS.getMessageDetails(), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<String>(MessageContainer.MESSAGE_IS_EXIST.getMessageDetails(), HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<String>(MessageContainer.MESSAGE_IS_FOUND_NULL.getMessageDetails(), HttpStatus.NOT_FOUND);
            }
        }else{
           return new ResponseEntity<String>(MessageContainer.MESSAGE_FOREIGN_KEY_EXIST.getMessageDetails(), HttpStatus.NOT_FOUND);
        }

    }

    // This method is for find by user progress Id
    @GetMapping("/find-userProgress/by/{userProgressId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userProgressId) {
        UserProgressDto userProgressDto = userProgressService.findByUserProgressID(userProgressId);
        if(userProgressDto==null || userProgressDto.equals("")){
            return new ResponseEntity<String>(MessageContainer.MESSAGE_DATA_EXIST.getMessageDetails(), HttpStatus.FOUND);
        }else{
            return new ResponseEntity<UserProgressDto>(userProgressService.findByUserProgressID(userProgressId), HttpStatus.FOUND);
        }

    }

    // This method is for update user progress
    @PutMapping(path="/update-user-progress-info")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserProgressDto userProgressDto) {
        userProgressService.saveOrUpdateUserProgressInfo(userProgressDto);
        return new ResponseEntity<String>(MessageContainer.MESSAGE_UPDATE_SUCCESS.getMessageDetails(), HttpStatus.ACCEPTED);
    }

}
