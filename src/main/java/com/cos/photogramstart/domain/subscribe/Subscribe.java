package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name="subscribe_uk", columnNames = {"fromUserId","toUSerId"})})
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