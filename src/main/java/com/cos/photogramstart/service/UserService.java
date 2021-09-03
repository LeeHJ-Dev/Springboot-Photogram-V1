package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.subscribe.SubScribeDto;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;                //회원정보 Repository
    private final ImageRepository imageRepository;              //사진정보 Repository
    private final SubscribeRepository subscribeRepository;      //구독정보 Repository
    private final BCryptPasswordEncoder encoder;                //패스워드 Encode

    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(Long pageUserId,Long principalId){
        User userEntity = userRepository.findById(pageUserId)
                .orElseThrow(()->{
                    throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
                });

        int mSubscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int mSubscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        return UserProfileDto.builder()
                .pageOwnerState(pageUserId == principalId)
                .imageCount(userEntity.getImages().size())
                .subscribeState(mSubscribeState>0?true:false)
                .subscribeCount(mSubscribeCount)
                .user(userEntity)
                .build();
    }


    /**
     * UserApiController -> 회원정보를 수정하는경우
     * @param id 회원정보 수정을 진행하는 userId
     * @param user 변경된 회원정보를 갖고 있는 UserUpdateDto
     * @return userEntity
     */
    @Transactional(readOnly = false)
    public User 회원수정(Long id, UserUpdateDto user) {
        /**
         * 회원Id 조회하여 JPA 영속성 컨텍스트 구성.
         */
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new CustomValidationApiException("찾을수없는 id 입니다.");
                });

        /**
         * 영속성 컨텍스트의 회원정보 변경
         *  - 회원수정() 거래가 종료되면 영속성 컨텍스트 Flush() -> DB 반영됨.
         */
        userEntity.setName(user.getName());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setWebsite(user.getWebsite());
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        //회원정보 수정
        return userEntity;
    }
}