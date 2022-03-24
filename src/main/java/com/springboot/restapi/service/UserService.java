package com.springboot.restapi.service;

import com.springboot.restapi.dto.UserDto;
import com.springboot.restapi.enums.ErrorMessageContainer;
import com.springboot.restapi.exception.BadRequestExceptionHandler;
import com.springboot.restapi.model.User;
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
public class UserService {


     @Autowired
     private UserRepository userRepository;

    public void saveOrUpdateUserInfo(UserDto userDto) {

        Optional.ofNullable(userDto).orElseThrow(() -> new BadRequestExceptionHandler(String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userDto")));

        User user = copyUserFromDtoOpt(Optional.ofNullable(userDto)).get();

        userRepository.save(user);
    }


    public Optional<User> copyUserFromDtoOpt(Optional<UserDto> userDtoOpt) {

        if (isUserDtoCopiable(userDtoOpt)) {

            return copyUserFromDTO(userDtoOpt.get());
        }

        return Optional.empty();
    }

    private boolean isUserDtoCopiable(Optional<UserDto> userDtoOpt) {

        UserDto userDto = userDtoOpt.orElseThrow(() -> new BadRequestExceptionHandler(
                String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userDto")));

        if (StringUtils.isEmpty(userDto.getName()))
            throw new BadRequestExceptionHandler(
                    String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userName"));

        return true;
    }

    private Optional<User> copyUserFromDTO(UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return Optional.of(user);

    }


   // find user info by id
    public UserDto findByUserID(Integer userId) {

        Optional<User> userOpt = userRepository.findById(userId);
//        if(! userOpt.isPresent()) {
//            throw new ResourceNotFoundException(String.format(ErrorMessageContainer.MESSAGE_TYPE_RESOURCE_NOT_FOUND.getMessageDetails(), "user ID: " + userId));
//        }
        return copyUserDtoFromEntityOpt(userOpt).get();
    }

    public Optional<UserDto> copyUserDtoFromEntityOpt(Optional<User> userOpt) {

        if (isUserCopiable(userOpt)) {
            return	copyUserDtoFromEntity(userOpt.get());
        }

        return Optional.empty();
    }

    private boolean isUserCopiable(Optional<User> usrOpt) {

        User user = usrOpt.orElseThrow(() -> new BadRequestExceptionHandler(
                String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "user")));

        if (StringUtils.isEmpty(user.getId()))
            throw new BadRequestExceptionHandler(
                    String.format(ErrorMessageContainer.MESSAGE_TYPE_BAD_REQUEST.getMessageDetails(), "userId"));

        return true;
    }

    private Optional<UserDto> copyUserDtoFromEntity(User user) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return Optional.of(userDto);

    }


}
