package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.ClassList;
import com.axeweb.parentorganizr.repository.ClassListRepository;
import com.axeweb.parentorganizr.service.ClassListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/classlist")
@CrossOrigin
public class ClassListController {

    private final ClassListRepository repository;
    private final ClassListService classService;

    public ClassListController(ClassListRepository repository, ClassListService classService) {
        this.repository = repository;
        this.classService = classService;
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
    public void create(@Valid @RequestPart("classlist") MultipartFile classList) throws IOException {
        classService.parseClassList(classList);
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
