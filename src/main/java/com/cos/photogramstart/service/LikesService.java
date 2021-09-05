package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional(readOnly = false)
    public void 좋아요(Long principalId, Long imageId){
        likesRepository.mLike(principalId,imageId);
    }

    @Transactional(readOnly = false)
    public void 좋아요취소(Long principalId, Long imageId){
        likesRepository.mUnLike(principalId,imageId);
    }


}
