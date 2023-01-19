package com.example3.demo;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author bsoto
 * @project demo3
 * @created at 19-01-2023
 */
public class CustomPredicate implements QuerydslBinderCustomizer<QPerson> {
    @Override
    public void customize(QuerydslBindings bindings, QPerson qPerson) {


        bindings.bind(qPerson.hobbies).first((path, value) -> path.any().in(value));

        bindings.bind(qPerson.name).first(StringExpression::startsWith);

        // Exclude id from filtering
        bindings.excluding(qPerson.id);

        // Make all string case-insensitive
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

        // Make a kind of 'between' filter for Person.age property
        bindings.bind(qPerson.age).all((path, value) -> {
            Iterator<? extends Integer> it = value.iterator();
            Integer from = it.next();
            if (value.size() >= 2) {
                Integer to = it.next();
                return Optional.of(path.between(from, to));
            } else {
                return Optional.of(path.eq(from));
            }
        });
    }
}
