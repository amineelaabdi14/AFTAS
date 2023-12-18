package com.example.demo.services;
import com.example.demo.entities.Hunt;


public interface HuntService {
    Hunt save(Hunt hunt, Double weight);

    Hunt findById(Long id);
}
