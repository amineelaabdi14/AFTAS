package com.example.demo.dto.rankingDTO;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Member;
import com.example.demo.entities.Ranking;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequestDto(
        @NotBlank String competitionCode,
        @NotNull Integer memberNum
) {
    public static Ranking toRanking(RegistrationRequestDto registrationRequestDto) {
        return Ranking.builder()
                .member(Member.builder().num(registrationRequestDto.memberNum()).build())
                .competition(Competition.builder().code(registrationRequestDto.competitionCode()).build())
                .build();
    }
}
