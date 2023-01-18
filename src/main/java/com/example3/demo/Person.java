package com.example3.demo;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

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

    public Person(LocalDate dob, String name, Integer age) {
        this.dob = dob;
        this.name = name;
        this.age = age;
    }
}
