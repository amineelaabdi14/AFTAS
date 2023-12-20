package com.example.demo.repositories;

import com.example.demo.entities.Competition;
import com.example.demo.entities.Ranking;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepo extends JpaRepository<Competition, String> {
    List<Competition> findAllByOrderByDateDesc(Pageable pageable);

    Optional<Competition> findByDate(@NotBlank LocalDate date);

    Optional<Competition> findByCodeLikeIgnoreCase(@NotBlank String code);

    @Query("SELECT c FROM Ranking c")
    List<Ranking> findMemberByCompetitionCode(String code);
}
