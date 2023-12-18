package com.example.demo.repositories;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Fish;
import com.example.demo.entities.Hunt;
import com.example.demo.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntRepo extends JpaRepository<Hunt, Long> {
    Optional<Hunt> findByCompetitionAndFishAndMember(Competition competition, Fish fish, Member member);
}
