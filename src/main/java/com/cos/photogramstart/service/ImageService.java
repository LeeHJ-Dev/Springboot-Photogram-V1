package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
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

    @Value(value = "${file.path}")
    private String uploadFolder;

    @Transactional(readOnly = false)
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid +"_"+imageUploadDto.getFile().getOriginalFilename(); //1.jpg <- full name
        System.out.println("imageFileName = " + imageFileName);

        System.out.println("uploadFolder = " + uploadFolder);
        Path imageFilePath = Paths.get(this.uploadFolder+imageFileName);

        //통신, i/o -> 예외가 발생할 수 있다.
        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {}

    }
}
