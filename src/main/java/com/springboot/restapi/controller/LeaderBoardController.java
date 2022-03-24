package com.springboot.restapi.controller;

import com.springboot.restapi.dto.TopScorerListDto;
import com.springboot.restapi.enums.MessageContainer;
import com.springboot.restapi.service.TopScorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by User on 3/23/2022.
 */

@RestController
@RequestMapping("/api")
public class LeaderBoardController {

    @Autowired
    private TopScorerService topScorerService;

    @GetMapping("/find-top-scorer")
    public ResponseEntity<?> findListOfTopScorer() {

        List<TopScorerListDto> isExit = topScorerService.findTopScorer();
        if(isExit.isEmpty()){
            return new ResponseEntity<String>(MessageContainer.MESSAGE_DATA_EXIST.getMessageDetails(), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<List<TopScorerListDto>>(topScorerService.findTopScorer(), HttpStatus.FOUND);
        }
    }
}
