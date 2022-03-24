package com.springboot.restapi.controller;

import com.springboot.restapi.dto.UserDto;
import com.springboot.restapi.enums.MessageContainer;
import com.springboot.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by User on 3/23/2022.
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // This method is for add user
    @PostMapping("/save-user-info")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        userService.saveOrUpdateUserInfo(userDto);
        return new ResponseEntity<String>(MessageContainer.MESSAGE_SAVE_SUCCESS.getMessageDetails(), HttpStatus.CREATED);
    }

    // This method is for find by user Id
    @GetMapping("/find-user/by/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userId) {
        UserDto userDto= userService.findByUserID(userId);
        if(userDto==null || userDto.equals("")){
            return new ResponseEntity<String>(MessageContainer.MESSAGE_DATA_EXIST.getMessageDetails(), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<UserDto>(userService.findByUserID(userId), HttpStatus.FOUND);
        }
    }

    // This method is for Updated for user info
    @PutMapping(path="/update-user-info")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto userDto) {
        userService.saveOrUpdateUserInfo(userDto);
        return new ResponseEntity<String>(MessageContainer.MESSAGE_UPDATE_SUCCESS.getMessageDetails(), HttpStatus.ACCEPTED);
    }

}
