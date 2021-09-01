package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    /**
     * 사용자가 특정 사용자를 구독하는 경우
     * @param formUserId 사용자 userId
     * @param toUserId 구독자 userId
     */
    @Transactional(readOnly = false)
    public void 구독하기(Long formUserId, Long toUserId) {
        try {
            subscribeRepository.mSubscribe(formUserId, toUserId);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    /**
     * 사용자가 등록된 구독자를 삭제하는 경우
     * @param formUserId 사용자 userId
     * @param toUserId 구독자 userId
     */
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