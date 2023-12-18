package com.example.demo.services;
import com.example.demo.entities.Fish;

import java.util.List;

public interface FishService {
    Fish save(Fish fish);

    Fish findById(Long id);

    void deleteById(Long id);

    void delete(Fish entity);

    List<Fish> findAll();
}
