package com.example.techassessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue
    Integer id;

    @NotNull
    String name;
}
