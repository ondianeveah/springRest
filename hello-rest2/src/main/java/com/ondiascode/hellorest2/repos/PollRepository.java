package com.ondiascode.hellorest2.repos;

import com.ondiascode.hellorest2.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
