package com.axeweb.parentorganizr.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import com.axeweb.parentorganizr.model.ClassList;
import org.springframework.stereotype.Repository;

@Repository
public class ClassListCollectionRepository {
    private final Map<String, ClassList> classLists = new HashMap<>();

    public ClassListCollectionRepository() {
        classLists.put("1A", new ClassList("1", "A", "1A", "2021", "1A.csv"));
        classLists.put("1B", new ClassList("1", "B", "1B", "2021", "1B.csv"));
        classLists.put("2A", new ClassList("2", "A", "2A", "2020", "2A.csv"));
        classLists.put("2B", new ClassList("2", "B", "2B", "2020", "2B.csv"));
    }

    public Collection<ClassList> findAll() {
        return classLists.values();
    }

    public Optional<ClassList> findById(String id) {
        return Optional.ofNullable(classLists.get(id));
    }

    public void save(ClassList classList) {
        String id = classList.classNumber() + classList.classLetter();
        classLists.put(id, classList);
    }

    public void remove(String id) {
        classLists.remove(id);
    }

    public boolean existsById(String id) {
        return classLists.containsKey(id);
    }
}
