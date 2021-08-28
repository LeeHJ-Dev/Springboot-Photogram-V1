package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String website;     //웹사이트

    @Column
    private String bio;         //자기소개

    @Column
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
}
