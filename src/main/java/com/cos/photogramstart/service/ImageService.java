package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

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