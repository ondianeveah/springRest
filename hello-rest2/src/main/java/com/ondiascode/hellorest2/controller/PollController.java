package com.ondiascode.hellorest2.controller;

import com.ondiascode.hellorest2.domain.Poll;
import com.ondiascode.hellorest2.exception.ResourceNotFoundException;
import com.ondiascode.hellorest2.repos.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ondiascode.hellorest2.service.PollService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if(poll == null){
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found...");
        }
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPolls (){
        Poll p = (Poll) pollService.getAllPolls();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPollById(@PathVariable Long id){
        verifyPoll(id);
        Optional<Poll> p = pollService.getPollById(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> addPollById(@RequestBody Poll poll){
        pollService.addPollById(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePollById(@PathVariable Long pollId, @RequestBody Poll poll){
        verifyPoll(pollId);
        pollService.updatePollById(pollId, poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePollById(@PathVariable Long id){
        verifyPoll(id);
        pollService.deletePollById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
