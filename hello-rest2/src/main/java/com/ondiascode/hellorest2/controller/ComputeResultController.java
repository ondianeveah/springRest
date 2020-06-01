package com.ondiascode.hellorest2.controller;

import com.ondiascode.hellorest2.dto.OptionCount;
import com.ondiascode.hellorest2.domain.Vote;
import com.ondiascode.hellorest2.dto.VoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ondiascode.hellorest2.repos.VoteRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController<allVotes> {

    @Autowired
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId){
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for(Vote v : allVotes){
            totalVotes++;
            //Get the OptionCount
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null){
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
