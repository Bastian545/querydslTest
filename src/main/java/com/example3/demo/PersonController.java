package com.example3.demo;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example3.demo.QPerson;

import java.util.List;

/**
 * @author bsoto
 * @project demo3
 * @created at 18-01-2023
 */
@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    private PersonRepo personRepo;
    @GetMapping
    public ResponseEntity getFiltered(@QuerydslPredicate(root = Person.class,bindings = CustomPredicate.class) Predicate predicate) {
        return ResponseEntity.ok(personRepo.findAll(predicate));
    }
}