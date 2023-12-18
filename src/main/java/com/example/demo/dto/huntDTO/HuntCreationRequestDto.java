package com.example.demo.dto.huntDTO;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Fish;
import com.example.demo.entities.Hunt;
import com.example.demo.entities.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HuntCreationRequestDto(
        @NotBlank(message = "Competition code is mandatory")
        String competition_code,
        @NotNull(message = "Fish ID is mandatory")
        Long fishId,
        @NotNull(message = "Member ID is mandatory")
        Integer memberNum,
        @NotNull(message = "Weight is mandatory")
        Double weight) {

    public static Hunt toHunt(HuntCreationRequestDto huntCreationRequestDto) {
        return Hunt.builder()
                .competition(Competition.builder().code(huntCreationRequestDto.competition_code()).build())
                .fish(Fish.builder().id(huntCreationRequestDto.fishId()).build())
                .member(Member.builder().num(huntCreationRequestDto.memberNum()).build())
                .build();
    }
}
