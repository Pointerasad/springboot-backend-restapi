package com.springboot.restapi.dto;

import com.springboot.restapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by User on 3/23/2022.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressDto {

    private Integer id;
    private Integer level;
    private Float score;
    private Integer userId;
}
