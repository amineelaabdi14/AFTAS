package com.example.demo.entities;


import com.example.demo.embeddable.RankId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Ranking {
    @EmbeddedId
    private RankId id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @MapsId("memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_code")
    @MapsId("competitionCode")
    private Competition competition;

    @ColumnDefault("0")
    private Integer rank;

    @ColumnDefault("0")
    private Integer score;

}
