package com.example.techassessment.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    Integer id;
    @NotNull
    @Length(min = 14, max = 14)
    private String nationalId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private int age;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="branch_id")
    Branch branch;

}
