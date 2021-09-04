package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints ={
        @UniqueConstraint(name = "like_uk", columnNames = {"imageId","userId"})
})
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn(name = "imageId")
    @ManyToOne(fetch = FetchType.EAGER)
    @Column
    private Image image;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    @Column
    private User user;

    @Column
    private LocalDateTime createDate;

}
