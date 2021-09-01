package com.cos.photogramstart.web.dto.image;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * MultipartFile Class
 */
@Data
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;

    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(this.caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
