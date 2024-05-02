package com.axeweb.parentorganizr.contoller;

import com.axeweb.parentorganizr.model.Person;
import com.axeweb.parentorganizr.model.Pupil;
import com.axeweb.parentorganizr.repository.PupilRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pupil")
@CrossOrigin
public class PupilController {

    private final PupilRepository repository;

    public PupilController(PupilRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Pupil> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
// TODO: Implement the findByName method
//    @GetMapping("/{name}")
//    public List<Pupil> findByName(@PathVariable String name) {
//        return repository.findByName(name);
//    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Pupil pupil) {
        repository.save(pupil);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Pupil person) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pupil id was not found");
        }
        repository.save(person);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
