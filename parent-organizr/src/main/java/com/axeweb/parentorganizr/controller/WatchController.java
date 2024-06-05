package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.controller.exception.WatchNotFoundException;
import com.axeweb.parentorganizr.model.Watch;
import com.axeweb.parentorganizr.repository.WatchRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watches")
public class WatchController {

    private final WatchRepository watchRepository;

    public WatchController(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @GetMapping("")
    public List<Watch> findAll() {
        return watchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Watch findById(@PathVariable Integer id) {
        return watchRepository.findById(id).orElseThrow(WatchNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Watch create(@RequestBody Watch watch) {
        return watchRepository.save(watch);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Watch update(@PathVariable Integer id, @RequestBody @Valid Watch watch) {
        Watch existingWatch = watchRepository.findById(id).orElseThrow(WatchNotFoundException::new);
        existingWatch.setTask(watch.getTask());
        existingWatch.setParent(watch.getParent());
        existingWatch.setWatchDate(watch.getWatchDate());
        existingWatch.setStartTime(watch.getStartTime());
        if (watch.getEndTime() != null) {
            existingWatch.setEndTime(watch.getEndTime());
        }
        return watchRepository.save(existingWatch);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        watchRepository.deleteById(id);
    }
}
