package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubScribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

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

    /**
     * 페이지 유저의 구독목록 정보 조회
     * @param principalId 회원(세션) userId
     * @param pageUserId 페이지 userId
     */
    @Transactional(readOnly = true)
    public List<SubScribeDto> 구독리스트(Long principalId, Long pageUserId){

        //네이티브 쿼리 작성
        StringBuffer sb = new StringBuffer();
        sb.append("select t1.id, t1.username, t1.profileImageUrl ");
        sb.append("     , (select true from subscribe x where x.fromUserId = ? and toUserId = ?) as subscribeState ");
        sb.append("     , (? = t1.id) as equalUserState ");
        sb.append("  from user t1, subscribe t2 ");
        sb.append(" where t1.id = t2.toUserId ");
        sb.append("   and t2.fromUserId = ? ");

        //네이티브 쿼리 결과값
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        //쿼리실행
        JpaResultMapper resultMapper = new JpaResultMapper();
        List<SubScribeDto> subScribeDtos = resultMapper.list(query, SubScribeDto.class);
        return subScribeDtos;
    }
}