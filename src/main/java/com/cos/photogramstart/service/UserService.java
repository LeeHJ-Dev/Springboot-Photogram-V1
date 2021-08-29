package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = false)
    public User 회원수정(Long id, UserUpdateDto user){
        //회원정보 조회 - JPA 영속성 컨텍스트 구성
        User userEntity = userRepository.findById(id).orElseThrow(()->{
            throw new CustomValidationApiException("찾을수없는 id 입니다.");
        });

        //회원정보 셋팅
        userEntity.setName(user.getName());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setWebsite(user.getWebsite());
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        //회원정보 수정
        //User saveUser = userRepository.save(userEntity);
        return userEntity;
    }
}