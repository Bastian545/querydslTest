package com.example3.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * @author bsoto
 * @project demo3
 * @created at 18-01-2023
 */
@Getter
@Setter
@QueryEntity
@Document
@NoArgsConstructor
public class Person {
    @Id
    private String id;
    private LocalDate dob; // date of birth
    private String name;
    private Integer age;
    private List<String> hobbies;
    @QueryType(PropertyType.NONE)
    @JsonIgnore
    private String omitido;

    public Person(LocalDate dob, String name, Integer age, List<String> hobbies, String omitido) {
        this.dob = dob;
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.omitido = omitido;
    }
}
