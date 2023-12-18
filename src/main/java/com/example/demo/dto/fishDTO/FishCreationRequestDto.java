package com.example.demo.dto.fishDTO;
import com.example.demo.entities.Fish;
import com.example.demo.entities.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FishCreationRequestDto(@NotBlank(message = "Fish name is mandatory") String name,
                                     @NotNull(message = "Fish average weight is mandatory") Double averageWeight,
                                     @NotNull(message = "Fish level is mandatory") Long levelId) {

    public static Fish toFish(FishCreationRequestDto fishCreationRequestDto) {
        return Fish.builder().name(fishCreationRequestDto.name()).averageWeight(fishCreationRequestDto.averageWeight()).level(Level.builder().build().builder().id(fishCreationRequestDto.levelId()).build()).build();
    }
}
