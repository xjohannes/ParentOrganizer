package com.axeweb.parentorganizr.repository;

import com.axeweb.parentorganizr.model.Task;
import org.springframework.data.repository.ListCrudRepository;

public interface TaskRepository extends ListCrudRepository<Task, Integer> {
}
