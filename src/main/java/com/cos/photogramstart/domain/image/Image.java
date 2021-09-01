package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String caption; //사진설명

    @Column
    private String postImageUrl; //사진전송받아서 사진을 서버에 특정폴더에 저장.--db에는 그 저장된 경로를 insert

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //이미지좋아요
    //이미지댓글

    @Column
    private LocalDateTime createDate;

    @PostPersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    /*****************************************************************************************************/
    /* 사용자가 등록한 사진에 대한 연관관계 맵핑  */
    /*****************************************************************************************************/
    public void addImage(){
        this.user.addImage(this);
    }
}
