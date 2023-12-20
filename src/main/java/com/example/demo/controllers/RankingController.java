package com.example.demo.controllers;

import com.example.demo.dto.competitionDTO.CompetitionRankingsResponseDto;
import com.example.demo.dto.memberDTO.MemberResponseDto;
import com.example.demo.dto.rankingDTO.RankingResponseDto;
import com.example.demo.dto.rankingDTO.RegistrationRequestDto;
import com.example.demo.services.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rankings")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @PostMapping
    public ResponseEntity<RankingResponseDto> registerMember(@RequestBody @Valid RegistrationRequestDto registrationRequestDto) {
        return ResponseEntity.ok(RankingResponseDto.fromRanking(rankingService.registerMember(RegistrationRequestDto.toRanking(registrationRequestDto))));
    }


    @GetMapping("/competitions/{code}")
    public ResponseEntity<CompetitionRankingsResponseDto> findByCompetitionCode(@PathVariable String code) {
        return ResponseEntity.ok(CompetitionRankingsResponseDto.fromCompetitionAndRankings(code, RankingResponseDto.fromRankings(rankingService.findByCompetition(code))));
    }

    @GetMapping("/competitions/{code}/podium")
    public ResponseEntity<CompetitionRankingsResponseDto> findPodiumByCompetitionCode(@PathVariable String code) {
        return ResponseEntity.ok(CompetitionRankingsResponseDto.fromCompetitionAndRankings(code, RankingResponseDto.fromRankings(rankingService.findPodiumByCompetitionCode(code))));
    }



}
