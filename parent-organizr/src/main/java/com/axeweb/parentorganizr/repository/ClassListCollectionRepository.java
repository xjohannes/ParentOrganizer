package com.axeweb.parentorganizr.repository;

import com.axeweb.parentorganizr.model.ClassList;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ClassListCollectionRepository {
    private final Map<Integer, ClassList> classLists = new HashMap<>();

    public ClassListCollectionRepository() {
        // This class was used to simulate a database and is not used any more.
        // I keep it here for reference.
    }

    public Collection<ClassList> findAll() {
        return classLists.values();
    }

    public Optional<ClassList> findById(int id) {
        return Optional.ofNullable(classLists.get(id));
    }

    public void save(ClassList classList) {
        int id = classList.getId();
        classLists.put(id, classList);
    }

    public void remove(int id) {
        classLists.remove(id);
    }

    public boolean existsById(String id) {
        return classLists.containsKey(id);
    }
}
