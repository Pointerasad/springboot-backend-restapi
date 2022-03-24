package com.springboot.restapi.dto;

import com.springboot.restapi.model.UserProgress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by User on 3/23/2022.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String country;

}
