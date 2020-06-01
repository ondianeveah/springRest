package com.ondiascode.hellorest2.service;

import com.ondiascode.hellorest2.domain.Poll;
import com.ondiascode.hellorest2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ondiascode.hellorest2.repos.PollRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if(poll == null){
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found...");
        }
    }

    public List<Poll> getAllPolls(){
        List<Poll> listOfPolls = new ArrayList<>();
        pollRepository.findAll()
                .forEach(listOfPolls::add);
        return listOfPolls;
    }

    public Optional<Poll> getPollById(Long id){
        verifyPoll(id);
        return pollRepository.findById(id);
    }

    public void addPollById(Poll poll){
         pollRepository.save(poll);
    }

    public void updatePollById(Long id, Poll poll){
        verifyPoll(id);
        pollRepository.save(poll);
    }

    public void deletePollById(Long id){
        verifyPoll(id);
        pollRepository.deleteById(id);
    }

}
