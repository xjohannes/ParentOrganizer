package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.controller.exception.ParentNotFoundException;
import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.repository.ParentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("parents")
public class ParentController {

    private final ParentRepository parentRepository;

    public ParentController(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @GetMapping("")
    List<Parent> findAll() {
        return parentRepository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Parent> findById(@PathVariable Integer id) {
        return Optional.ofNullable(parentRepository.findById(id)
                .orElseThrow(ParentNotFoundException::new));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Parent create(@RequestBody @Valid Parent parent) {
        return parentRepository.save(parent);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    Parent update(@PathVariable Integer id, @RequestBody @Valid Parent parent) {
      Optional<Parent> existingParent =  parentRepository.findById(id);
        if (existingParent.isPresent()) {
            existingParent.get().setFirstName(parent.getFirstName());
            existingParent.get().setLastName(parent.getLastName());
            if (parent.getEmail() != null) {
                existingParent.get().setEmail(parent.getEmail());
            }
            if (parent.getPhoneNumber() != null) {
                existingParent.get().setPhoneNumber(parent.getPhoneNumber());
            }
            if (parent.getChildren() != null) {
                existingParent.get().setChildren(parent.getChildren());
            }

            return parentRepository.save(existingParent.get());
        } else {
            throw new ParentNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        parentRepository.deleteById(id);
    }

}
