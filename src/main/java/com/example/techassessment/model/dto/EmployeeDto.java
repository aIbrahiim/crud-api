package com.example.techassessment.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EmployeeDto {
    Integer id;

    @NotNull
    @NotEmpty
    @Length(min = 14, max = 14, message = "National id length should be 14")
    private String nationalId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private int age;

    BranchDto branch;
}
