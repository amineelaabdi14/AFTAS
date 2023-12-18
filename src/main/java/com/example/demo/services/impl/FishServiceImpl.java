package com.example.demo.services.impl;
import com.example.demo.entities.Fish;
import com.example.demo.repositories.FishRepo;
import com.example.demo.services.FishService;
import com.imsouane.aftas.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

    private final FishRepo fishRepository;

    @Override
    public Fish save(Fish fish) {
        return fishRepository.save(fish);
    }

    @Override
    public Fish findById(Long id) {
        return fishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fish not found"));
    }

    @Override
    public void deleteById(Long id) {
        Fish fish = findById(id);
        fishRepository.delete(fish);
    }

    @Override
    public void delete(Fish entity) {
        fishRepository.delete(entity);
    }

    @Override
    public List<Fish> findAll() {
        return fishRepository.findAll();
    }
}
