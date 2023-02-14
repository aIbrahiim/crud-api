package com.example.techassessment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel implements Serializable {

    private int code;
    private String message;
}
