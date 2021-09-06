package com.cos.photogramstart.web.api;
import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping(value = "/api/comment")
    public ResponseEntity<?> commentSave(@RequestBody CommentDto commentDto,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails){
        Comment comment = commentService.댓글등록(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());

        return new ResponseEntity<>(new CMRespDto<>(1,"댓글등록", comment), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        commentService.댓글삭제(id);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제",null), HttpStatus.OK);
    }
}
