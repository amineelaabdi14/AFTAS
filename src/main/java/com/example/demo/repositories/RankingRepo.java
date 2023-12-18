package com.example.demo.repositories;

import com.example.demo.embeddable.RankId;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepo extends JpaRepository<Ranking, RankId> {
    List<Ranking> findByCompetitionCode(String code);

    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);

    Optional<Ranking> findByCompetitionCodeAndMemberNum(String code, Integer num);

    Boolean existsByCompetitionCodeAndMemberNum(String code, Integer num);

    List<Ranking> findTop3ByCompetitionCodeOrderByScoreDesc(String code);

    List<Ranking> findByCompetitionCodeOrderByScoreDesc(String code);

    Integer countByCompetitionCode(String code);
}
