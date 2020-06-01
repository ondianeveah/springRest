package com.ondiascode.hellorest2.repos;

import com.ondiascode.hellorest2.domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
