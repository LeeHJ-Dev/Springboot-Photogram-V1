package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UserUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private String website;

    private String bio;

    private String phone;

    private String gender;

    public User toEntity(){
        return User.builder()
                .name(this.name)
                .password(this.password)
                .website(this.website)
                .bio(this.bio)
                .phone(this.phone)
                .gender(this.gender)
                .build();
    }
}
