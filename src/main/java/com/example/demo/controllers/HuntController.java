package com.example.demo.controllers;

import com.example.demo.dto.huntDTO.HuntCreationRequestDto;
import com.example.demo.dto.huntDTO.HuntResponseDto;
import com.example.demo.entities.Hunt;
import com.example.demo.services.HuntService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hunts")
public class HuntController {
    private final HuntService huntService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HuntResponseDto save(@RequestBody @Valid HuntCreationRequestDto hunt) {
        Hunt toCreateHunt = HuntCreationRequestDto.toHunt(hunt);
        Hunt savedHunt = huntService.save(toCreateHunt, hunt.weight());
        return HuntResponseDto.fromHunt(savedHunt);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuntResponseDto> findById(@PathVariable Long id) {
        HuntResponseDto huntResponseDto = HuntResponseDto.fromHunt(huntService.findById(id));
        return ResponseEntity.ok(huntResponseDto);
    }
}
