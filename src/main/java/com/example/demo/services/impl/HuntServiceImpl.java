package com.example.demo.services.impl;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Fish;
import com.example.demo.entities.Hunt;
import com.example.demo.entities.Member;
import com.example.demo.repositories.HuntRepo;
import com.example.demo.services.*;
import com.imsouane.aftas.exception.HuntCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HuntServiceImpl implements HuntService {
    private final HuntRepo huntRepository;
    private final FishService fishService;
    private final CompetitionService competitionService;
    private final MemberService memberService;
    private final RankingService rankingService;

    @Override
    public Hunt save(Hunt hunt, Double weight) {
        Fish fish = fishService.findById(hunt.getFish().getId());
        Member member = memberService.findByNum(hunt.getMember().getNum());
        Competition competition = competitionService.findByCode(hunt.getCompetition().getCode());
        if (competition.getDate().isEqual(LocalDate.now())) {
            throw new HuntCreationException("Hunts can only be registered on the day of the competition");
        }
        if (fish.getAverageWeight() <= weight) {
            Hunt hunt1 = huntRepository.findByCompetitionAndFishAndMember(competition, fish, member).orElse(null);
            if (hunt1 == null) {
                hunt1 = Hunt.builder()
                        .competition(competition)
                        .fish(fish)
                        .member(member)
                        .numberOfFish(1)
                        .build();
            } else {
                hunt1.setNumberOfFish(hunt1.getNumberOfFish() + 1);
            }
            rankingService.updateRankingScoreAndRank(member, competition, fish);
            return huntRepository.save(hunt1);
        } else {
            throw new HuntCreationException("Weight is less than average weight");
        }
    }

    @Override
    public Hunt findById(Long id) {
        return huntRepository.findById(id).orElseThrow(() -> new HuntCreationException("Hunt not found"));
    }
}
