package com.axeweb.parentorganizr.contoller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.axeweb.parentorganizr.model.ClassList;
import com.axeweb.parentorganizr.repository.ClassListRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/classlist")
@CrossOrigin 
public class ClassListController {

    private final ClassListRepository repository;

    public ClassListController(ClassListRepository repository) {
        this.repository = repository;
    }
    
    
    @GetMapping
    public Collection<ClassList> classList() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ClassList get(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classlist not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody ClassList classList) {
        repository.save(classList);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable String id, @Valid @RequestBody ClassList classList) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Classlist id was not found");
        }
        repository.save(classList);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
       repository.deleteById(id);
    }
    
}
