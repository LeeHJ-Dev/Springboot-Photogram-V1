package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private String website;     //웹사이트

    @Column
    private String bio;         //자기소개

    @Column(nullable = false)
    private String email;

    @Column
    private String phone;

    @Column
    private String gender;

    @Column
    private String profileImageUrl;  //사진

    @Column
    private String role;            //권한

    @Column
    private LocalDateTime createDate;

    /*****************************************************************************************************/
    /* */
    /*****************************************************************************************************/
    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    /*****************************************************************************************************/
    /* 사용자가 등록한 사진에 대한 연관관계 맵핑  */
    /*****************************************************************************************************/
    //나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지 마.
    //User를 select할때 해당 User id 로 등록된 image들을 다 가져와.
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<Image>();        //양방향 맵핑

    public void addImage(Image image){
        this.images.add(image);

    }

}
