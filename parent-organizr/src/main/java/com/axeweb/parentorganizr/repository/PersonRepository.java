package com.axeweb.parentorganizr.repository;

import com.axeweb.parentorganizr.model.Person;
import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends ListCrudRepository<Person, Integer>{

}
