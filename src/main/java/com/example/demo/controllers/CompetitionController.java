package com.example.demo.controllers;
import com.example.demo.dto.competitionDTO.CompetitionCreationRequestDto;
import com.example.demo.dto.competitionDTO.CompetitionResponseDto;
import com.example.demo.entities.Competition;
import com.example.demo.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Iterable<CompetitionResponseDto>> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(CompetitionResponseDto.fromCompetitions(competitionService.findAll(PageRequest.of(page, size))));
    }

    @GetMapping("/{code}")
    public ResponseEntity<CompetitionResponseDto> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(CompetitionResponseDto.fromCompetition(competitionService.findByCode(code)));
    }
}
