package com.springboot.restapi.service;

import com.springboot.restapi.dto.TopScorerListDto;
import com.springboot.restapi.enums.ErrorMessageContainer;
import com.springboot.restapi.exception.ResourceNotFoundException;
import com.springboot.restapi.model.UserProgress;
import com.springboot.restapi.repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by User on 3/25/2022.
 */

@Service
public class TopScorerService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    public List<TopScorerListDto> findTopScorer() {

        List<UserProgress> userProgressList = userProgressRepository.findTop10ByOrderByScoreDesc();

//        if (userProgressList.isEmpty()) {
//            throw new ResourceNotFoundException(ErrorMessageContainer.MESSAGE_TYPE_RESOURCE_NOT_FOUND.getMessageDetails());
//        }
//      List<TopScorerListDto> topScorerDTOList = userProgressList.stream().map(urProgress -> copyStudentDtoFromEntityOpt(Optional.of(urProgress)).get()).collect(Collectors.toList());
        return convertModelToDto(userProgressList);

    }

//    public Optional<TopScorerListDto> copyStudentDtoFromEntityOpt(Optional<UserProgress> userProgressOpt) {
//
//            return	copyStudentDtoFromEntity(userProgressOpt.get());
//    }

    private List<TopScorerListDto> convertModelToDto(List<UserProgress> userProgress) {

        Integer count=1;
        List<TopScorerListDto> list = new ArrayList();

        for(UserProgress o :userProgress){
            TopScorerListDto topScorerListDtos = new TopScorerListDto();
            topScorerListDtos.setRank(count++);
            topScorerListDtos.setLevel(o.getLevel());
            topScorerListDtos.setName(o.getUser().getName());
            topScorerListDtos.setScore(o.getScore());
            list.add(topScorerListDtos);
        }

        return list;

    }

}
