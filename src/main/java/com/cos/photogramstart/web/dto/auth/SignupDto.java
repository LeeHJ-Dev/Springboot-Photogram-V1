package com.cos.photogramstart.web.dto.auth;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {
    @Size(min = 1, max = 20)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .name(this.name)
                .build();


    }
}
