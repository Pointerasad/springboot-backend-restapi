package com.springboot.restapi.dto;

import lombok.Data;

/**
 * Created by User on 3/25/2022.
 */

@Data
public class TopScorerListDto {

    private Integer rank;
    private Integer level;
    private String name;
    private Float score;

}
