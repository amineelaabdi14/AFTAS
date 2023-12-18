package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Competition {
    @Id
    private String code;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @NotNull
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;

    @NotNull
    @Temporal(TemporalType.TIME)
    private LocalTime endTime;

    @NotNull
    private Integer numberOfParticipants;

    @NotBlank
    private String location;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "competition")
    private List<Hunt> hunts;
}
