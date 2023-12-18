package com.example.demo.services.impl;
import com.example.demo.embeddable.RankId;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Fish;
import com.example.demo.entities.Member;
import com.example.demo.entities.Ranking;
import com.example.demo.repositories.RankingRepo;
import com.example.demo.services.CompetitionService;
import com.example.demo.services.MemberService;
import com.example.demo.services.RankingService;
import com.imsouane.aftas.exception.RankingCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final RankingRepo rankingRepository;
    private final CompetitionService competitionService;
    private final MemberService memberService;

    @Override
    public List<Ranking> findByCompetitionCode(String code) {
        return rankingRepository.findByCompetitionCode(code);
    }

    @Override
    public List<Ranking> findByCompetition(String code) {
        Competition competition = competitionService.findByCode(code);
        return rankingRepository.findByCompetitionOrderByScoreDesc(competition);
    }

    @Override
    public Ranking registerMember(Ranking ranking) {
        Competition competition = competitionService.findByCode(ranking.getCompetition().getCode());
        Member member = memberService.findByNum(ranking.getMember().getNum());
        if (competition.getNumberOfParticipants() <= getCurrentNumberOfParticipants(competition.getCode())) {
            throw new RankingCreationException("Competition is full");
        }
        if (rankingRepository.existsByCompetitionCodeAndMemberNum(competition.getCode(), member.getNum())) {
            throw new RankingCreationException("Member already registered in this competition");
        }
        if (LocalDateTime.of(competition.getDate(), competition.getStartTime()).minusHours(24).isBefore(LocalDateTime.now())) {
            throw new RankingCreationException("Registration is closed");
        }
        ranking.setId(new RankId(member.getId(), competition.getCode()));
        ranking.setMember(member);
        ranking.setCompetition(competition);
        ranking.setScore(0);
        ranking.setRank(getCurrentNumberOfParticipants(competition.getCode()) + 1);
        return rankingRepository.save(ranking);
    }

    @Override
    public void updateRankingScoreAndRank(Member member, Competition competition, Fish fish) {
        Ranking ranking = rankingRepository.findByCompetitionCodeAndMemberNum(competition.getCode(), member.getNum()).orElseThrow(() -> new RankingCreationException("Member not registered in this competition"));
        ranking.setScore(ranking.getScore() + fish.getLevel().getPoints());
        rankingRepository.save(ranking);
        List<Ranking> rankings = rankingRepository.findByCompetitionCodeOrderByScoreDesc(competition.getCode());
        rankings.forEach(r -> r.setRank(rankings.indexOf(r) + 1));
        rankingRepository.saveAll(rankings);
    }

    @Override
    public Integer getCurrentNumberOfParticipants(String code) {
        return rankingRepository.countByCompetitionCode(code);
    }

    @Override
    public List<Ranking> findPodiumByCompetitionCode(String code) {
        List<Ranking> rankings = rankingRepository.findTop3ByCompetitionCodeOrderByScoreDesc(code);
        if (rankings.isEmpty()) {
            throw new RankingCreationException("No podium found");
        } else {
            return rankings;
        }
    }
}
