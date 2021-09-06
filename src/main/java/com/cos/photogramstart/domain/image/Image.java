package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnoreProperties(value = {"images"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    //이미지좋아요 양방향 맵핑
    @JsonIgnoreProperties(value = {"image"})
    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<Likes> likes;

    @OrderBy(value = "id desc")
    @JsonIgnoreProperties(value = {"image"})
    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Transient
    private boolean likeState;

    @Transient
    private int likeCount;
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
