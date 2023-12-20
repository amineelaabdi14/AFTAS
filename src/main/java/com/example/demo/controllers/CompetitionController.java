package com.example.demo.controllers;
import com.example.demo.dto.competitionDTO.CompetitionCreationRequestDto;
import com.example.demo.dto.competitionDTO.CompetitionRankingsResponseDto;
import com.example.demo.dto.competitionDTO.CompetitionResponseDto;
import com.example.demo.dto.memberDTO.MemberResponseDto;
import com.example.demo.dto.rankingDTO.RankingResponseDto;
import com.example.demo.entities.Competition;
import com.example.demo.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<CompetitionResponseDto> save(@RequestBody @Valid CompetitionCreationRequestDto competition) {
        Competition toCreateCompetition = CompetitionCreationRequestDto.toCompetition(competition);
        return new ResponseEntity<>(CompetitionResponseDto.fromCompetition(competitionService.save(toCreateCompetition)), null, 201);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(competitionService.count());
    }
    @GetMapping
    public ResponseEntity<Iterable<CompetitionResponseDto>> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<CompetitionResponseDto> cc = CompetitionResponseDto.fromCompetitions(competitionService.findAll(PageRequest.of(page, size)));
        return ResponseEntity.ok(CompetitionResponseDto.fromCompetitions(competitionService.findAll(PageRequest.of(page, size))));
    }

    @GetMapping("/{code}")
    public ResponseEntity<CompetitionResponseDto> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(CompetitionResponseDto.fromCompetition(competitionService.findByCode(code)));
    }

}
