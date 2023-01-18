package com.example3.demo;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;


/**
 * @author bsoto
 * @project demo3
 * @created at 18-01-2023
 */
@Repository
public interface PersonRepo extends MongoRepository<Person, String>, QuerydslPredicateExecutor<Person>,
        QuerydslBinderCustomizer<QPerson> {

    @Override
    default void customize(QuerydslBindings bindings, QPerson model) {

        // Exclude id from filtering
        bindings.excluding(model.id);

        // Make all string case-insensitive
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

        // Make a kind of 'between' filter for Person.age property
        bindings.bind(model.age).all((path, value) -> {
            Iterator<? extends Integer> it = value.iterator();
            Integer from = it.next();
            if (value.size() >= 2) {
                Integer to = it.next();
                return Optional.of(path.between(from, to));
            } else {
                return Optional.of(path.eq(from));
            }
        });

        bindings.bind(model.dob).all((path, value) -> {
            Iterator<? extends LocalDate> it = value.iterator();
            LocalDate from = it.next();
            if (value.size() >= 2) {
                LocalDate to = it.next();
                return Optional.of(path.between(from, to));
            } else {
                return Optional.of(path.goe(from));
            }
        });
    }

}