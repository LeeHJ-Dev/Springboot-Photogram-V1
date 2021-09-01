package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;                    //사용자를 저장하기 위한 Repository
    private final BCryptPasswordEncoder bCryptPasswordEncoder;      //사용자가 입력한 Password encode

    /**
     * 사용자가 회원정보를 입력 후 회원가입을 진행하는 경우
     * @param user 회원장보 입력 값
     * @return userEntity
     */
    @Transactional(readOnly = false)
    public User 회원가입(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));     //패스워드 hash.
        user.setRole("ROLE_USER"); //사용자권한
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
