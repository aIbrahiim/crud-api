package com.example.techassessment.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class AbstractController <T, R> {

    protected ResponseEntity<R> handle(R body) {
        return ResponseEntity.ok(body);
    }

    protected ResponseEntity<List<R>> handle(List<R> body) {
        return ResponseEntity.ok(body);
    }
}
