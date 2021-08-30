package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    /**
     * 사용자 구독
     */
    @PostMapping(value = "/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PathVariable("toUserId") Long toUserId){
        System.out.println("SubscribeApiController.subscribe");
        System.out.println("principalDetails = " + principalDetails);
        System.out.println("toUserId = " + toUserId);
        subscribeService.구독하기(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독하기오류",null), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/subscribe/{toUserId")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @PathVariable("toUserId") Long toUserId){
        subscribeService.구독취소하기(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공",null),HttpStatus.OK);
    }
}