package com.example.demo.repositories;

import com.example.demo.embeddable.RankId;
import com.example.demo.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RankingRepo extends JpaRepository<Ranking, RankId> {
    @Query(value = "SELECT * FROM Ranking r WHERE r.competition_id = :competitionId AND (:specification IS NULL OR :specification)", nativeQuery = true)
    public Ranking getRankingsBy(String test);
}
