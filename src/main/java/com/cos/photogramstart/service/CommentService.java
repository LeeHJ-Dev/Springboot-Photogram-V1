package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Transactional(readOnly = false)
    public Comment 댓글등록(String content, Long imageId, Long userId){
        //시용자조회
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("사용자 없음");
        });

        //이미지조회
        Image imageEntity = imageRepository.findById(imageId).orElseThrow(() -> {
            throw new CustomApiException("이미지 없음");
        });

        //댓글등록
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(userEntity);
        comment.setImage(imageEntity);
        Comment saveEntity = commentRepository.save(comment);

        return saveEntity;
    }

    @Transactional(readOnly = false)
    public void 댓글삭제(Long commentId){
        //댓글조회
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new CustomApiException("댓글 없음");
        });
        //댓글삭제
        commentRepository.deleteById(comment.getId());
    }
}
