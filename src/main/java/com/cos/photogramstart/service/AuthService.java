package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    /**
     * 사용자가 회원가입정보를 입력 후 회원가입을 진행하는경우
     * @param user
     */
    public User 회원가입(User user){

        //패스워드 hash

        //사용자권한
        //user.setRole();
        User userEntity = userRepository.save(user);
        return userEntity;
    }


}
