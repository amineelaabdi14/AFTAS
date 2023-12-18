package com.example.demo.services;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Fish;
import com.example.demo.entities.Member;
import com.example.demo.entities.Ranking;

import java.util.List;

public interface RankingService {
    List<Ranking> findByCompetitionCode(String code);

    List<Ranking> findByCompetition(String code);

    Ranking registerMember(Ranking ranking);

    void updateRankingScoreAndRank(Member member, Competition competition, Fish fish);

    Integer getCurrentNumberOfParticipants(String code);

    List<Ranking> findPodiumByCompetitionCode(String code);
}
