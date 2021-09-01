package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 사용자가 회원가입 후 구독(다른 사용자)을 위한 정보관리 Entity Class.
 * 1. 사용자의 중복 구독관리를 제어하기 위해서 Table 제약속성
 *  - Column fromUserID + toUserId UniqueConstraints
 * 2. 연관관계 설정
 *  - @ManyToOne : User Table.(사용자), Fetch LAZY.
 *  - @ManyToOne : User Table.(구독자), Fetch LAZY.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name="subscribe_uk", columnNames = {"fromUserId","toUSerId"})})
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "fromUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JoinColumn(name = "toUSerId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    @Column
    private LocalDateTime createDate;

    @PostPersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}