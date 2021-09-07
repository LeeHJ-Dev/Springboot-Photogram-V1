package com.cos.photogramstart.web.api;
import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping(value = "/api/comment")
    public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDto commentDto,
                                         BindingResult bindingResult,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails){
        /* AOP 분리
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        }
        */
        Comment comment = commentService.댓글등록(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글등록", comment), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        commentService.댓글삭제(id);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제",null), HttpStatus.OK);
    }
}
