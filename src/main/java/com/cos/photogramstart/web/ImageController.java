package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
     * 사진 및 게시글 등록
     * @return
     */
    @PostMapping(value = "/image")
    public String imageUpload(ImageUploadDto imageUploadDto,
                              @AuthenticationPrincipal PrincipalDetails principalDetails){
        //서비스 호출
        imageService.사진업로드(imageUploadDto,principalDetails);

        //리턴 ->
        return "redirect:/user/"+principalDetails.getUser().getId();
    }


}
