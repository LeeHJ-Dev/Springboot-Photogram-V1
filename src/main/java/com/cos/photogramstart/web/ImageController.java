package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    /**
     * Path. Http://localhost:8080/ or Http://localhost:8080/image/story
     * @return
     */
    @GetMapping(value = {"/","/image/story"})
    public String story(){
        return "/image/story";
    }

    /**
     * Path. Http://localhost:8080/image/popular
     * @return
     */
    @GetMapping(value = "/image/popular")
    public String popular(){
        return "/image/popular";
    }

    /**
     * Path. Http://localhost:8080/image/upload
     * @return
     */
    @GetMapping(value = "/image/upload")
    public String upload(){
        return "/image/upload";
    }

    /**
     * 회원이 사진 및 게시글을 등록하는 경우
     * @param imageUploadDto
     * @param principalDetails 로그인한 사용자의 Authentication 주입
     * @return
     */
    @PostMapping(value = "/image")
    public String imageUpload(ImageUploadDto imageUploadDto,
                              @AuthenticationPrincipal PrincipalDetails principalDetails){
        /**
         * 게시글에 등록하는 사진이 없는경우
         */
        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.",null);
        }

        /**
         * 사진업로드
         */
        imageService.사진업로드(imageUploadDto,principalDetails);

        /**
         * redirect Path. Http://localhost:8080/user/{userId}
         */
        return "redirect:/user/"+principalDetails.getUser().getId();
    }
}
