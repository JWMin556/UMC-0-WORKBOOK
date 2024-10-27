package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float score;

    // Member와 다대일 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // foreign key column 이름
    private Member member;

    // 연관관계 편의 메서드 추가 (양방향 관계를 위한)
    public void setMember(Member member) {
        this.member = member;
        if (!member.getReviewList().contains(this)) {
            member.getReviewList().add(this);
        }
    }
}
