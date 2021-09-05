package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.likes.LikesRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final LikesRepository likesRepository;

    @Transactional(readOnly = true)
    public List<Image> 인기사진(){
        return imageRepository.mPopular();
    }


    /**
     * 사용자가 구독한 사용자의 사진정보를 페이징처리한다.
     * @param principalId
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Image> 이미지스토리(Long principalId, Pageable pageable){
        Page<Image> images = imageRepository.mStory(principalId, pageable);

        //이미지 리스트 조회
        //1번이미지
           //
        images.forEach(image->{
            image.setLikeCount(image.getLikes().size());
            image.getLikes().forEach((like)->{
                if(like.getUser().getId().equals(principalId)){
                    image.setLikeState(true);
                }
            });
        });

        return images;
    }


    /**
     * application.yml
     *  - Image File Path Value
     *  - /USers/lhj/study/Springboot-Photogram-V1/upload/
     */
    @Value(value = "${file.path}")
    private String uploadFolder;

    /**
     * 회원이 사진 및 게시글을 업로드 하는 경우
     * @param imageUploadDto
     * @param principalDetails
     */
    @Transactional(readOnly = false)
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        /**
         * ImageName (UUID + 파일이름)
         */
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid +"_"+imageUploadDto.getFile().getOriginalFilename(); //1.jpg <- full name
        System.out.println("imageFileName = " + imageFileName);

        System.out.println("uploadFolder = " + uploadFolder);
        Path imageFilePath = Paths.get(this.uploadFolder+imageFileName);

        /**
         * File.write
         *  - I/O Exception
         *  - 업로드 경로로 사진을 저장.
         *  - 경로, 이미지파일 Byte.
         */
        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {}

        /**
         * 업로드 사진 DB 저장.
         *  - 파일이름과 사용자의 정보를 이용해서 Image Class 저장.
         *  - Image Class(의) User field 연관관계 맵핑
         */
        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);
    }


}