package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    @GetMapping(value = "/api/image")
    public ResponseEntity<?> images(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Image> images = imageService.이미지스토리(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMRespDto<>(1,"사진스토리 조회성공",images), HttpStatus.OK);
    }

    @PostMapping(value = "/api/image/{imageId}/like")
    public ResponseEntity<?> likes(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @PathVariable("imageId") Long imageId){
        likesService.좋아요(principalDetails.getUser().getId(),imageId);
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요성공",null),HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/image/{imageId}/like")
    public ResponseEntity<?> unLikes(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @PathVariable("imageId") Long imageId){
        likesService.좋아요취소(principalDetails.getUser().getId(),imageId);
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요취소성공",null),HttpStatus.OK);
    }

}
