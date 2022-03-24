package com.springboot.restapi.service;

import com.springboot.restapi.dto.UserDto;
import com.springboot.restapi.dto.UserProgressDto;
import com.springboot.restapi.enums.ErrorMessageContainer;
import com.springboot.restapi.exception.BadRequestExceptionHandler;
import com.springboot.restapi.exception.ResourceNotFoundException;
import com.springboot.restapi.model.User;
import com.springboot.restapi.model.UserProgress;
import com.springboot.restapi.repository.UserProgressRepository;
import com.springboot.restapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;


/**
 * Created by User on 3/23/2022.
 */

@Service
@Transactional
public class UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;


    public void saveOrUpdateUserProgressInfo(UserProgressDto userProgressDto) {


        Optional.ofNullable(userProgressDto).orElseThrow(() ->
         new BadRequestExceptionHandler(String.format(
                 ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userProgressDto")
         ));

         UserProgress userProgress = copyUserProgressFromDtoOpt(Optional.ofNullable(userProgressDto)).get();
         userProgress.setUser(getUserProgressId(userProgressDto.getUserId()));
         userProgressRepository.save(userProgress);

    }

    public Optional<UserProgress> copyUserProgressFromDtoOpt(Optional<UserProgressDto> userProgressDtoOpt) {

        if (isUserProgressDtoCopiable(userProgressDtoOpt)) {

            return copyUserProgressFromDTO(userProgressDtoOpt.get());
        }

        return Optional.empty();
    }

    private boolean isUserProgressDtoCopiable(Optional<UserProgressDto> userProgressDtoOpt) {

        UserProgressDto userProgressDto = userProgressDtoOpt.orElseThrow(() -> new BadRequestExceptionHandler(
                String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userProgressDto")));

        if (StringUtils.isEmpty(userProgressDto.getLevel()) || StringUtils.isEmpty(userProgressDto.getScore()))
            throw new BadRequestExceptionHandler(
                    String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userScore"));

        return true;
    }

    private Optional<UserProgress> copyUserProgressFromDTO(UserProgressDto userProgressDto) {

        UserProgress userProgress = new UserProgress();
        BeanUtils.copyProperties(userProgressDto, userProgress);
        return Optional.of(userProgress);

    }

    public User getUserProgressId(Integer id){
        User user = new User();
        user.setId(id);
        return  user;
    }


    // find user progress info by id
    public UserProgressDto findByUserProgressID(Integer userProgressId) {

        Optional<UserProgress> userProgressOpt = userProgressRepository.findById(userProgressId);

        if(! userProgressOpt.isPresent()) {
            throw new ResourceNotFoundException(String.format(ErrorMessageContainer.MESSAGE_TYPE_RESOURCE_NOT_FOUND.getMessageDetails(), "User Progress ID: " + userProgressId));
        }

        return copyUserProgressDtoFromEntityOpt(userProgressOpt).get();
    }

    public Optional<UserProgressDto> copyUserProgressDtoFromEntityOpt(Optional<UserProgress> userProgressOpt) {

        if (isUserProgressCopiable(userProgressOpt)) {
            return	copyUserProgressDtoFromEntity(userProgressOpt.get());
        }

        return Optional.empty();
    }

    private boolean isUserProgressCopiable(Optional<UserProgress> userProgressOpt) {

        UserProgress userProgress = userProgressOpt.orElseThrow(() -> new BadRequestExceptionHandler(
                String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userProgress")));

        if (StringUtils.isEmpty(userProgress.getId()))
            throw new BadRequestExceptionHandler(
                    String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userProgressId"));

        return true;
    }

    private Optional<UserProgressDto> copyUserProgressDtoFromEntity(UserProgress userProgress) {

        UserProgressDto userProgressDto = new UserProgressDto();
        userProgressDto.setId(userProgress.getId());
        userProgressDto.setLevel(userProgress.getLevel());
        userProgressDto.setScore(userProgress.getScore());
        userProgressDto.setUserId(userProgress.getUser().getId());
//        BeanUtils.copyProperties(userProgress, userProgressDto);
        return Optional.of(userProgressDto);

    }


}
