package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    @Transactional(readOnly = false)
    public void 구독하기(Long formUserId, Long toUserId) {
        try {
            subscribeRepository.mSubscribe(formUserId, toUserId);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional(readOnly = false)
    public void 구독취소하기(Long formUserId, Long toUserId){
        try {
            subscribeRepository.mUnSubscribe(formUserId, toUserId);
        }catch(Exception e){
            e.printStackTrace();
            throw new CustomApiException("구독된 상태가 아닙니다.");
        }
    }
}
