package com.example.demo.services;

import com.example.demo.entities.Competition;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CompetitionService {
    List<Competition> findAll(Pageable pageable);

    Competition save(@Valid Competition competition);   

    Competition findByCode(String code);

    void delete(Competition competition);
}
