package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Authentication
 *  - AuthenticationPrincipalArgumentResolver Class resolveArgument Method.
 *   ex. Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 *   ex. PrincipalDetails principal = (PrincipalDetails)authentication.getPrincipal();
 *  - SecurityContext Authentication is null -> return null;
 *  - SecurityContext Authentication is not null -> Parameter principalDetails = (PrincipalDetails)principal
 *  - 인증정보가 저장되어 있다면 Parameter(인) PrincipalDetails Class binding..
 */
@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    /**
     * Path. Http://localhost:8080/api/subscribe/{userId}
     * 사용자가 특정 사용자에 대해서 구독을 진행하는 경우 사용자의 인증정보와 구독자의 userId를 이용해서 구독을 진행한다.
     * @param principalDetails 사용자가 로그인을 진행하는 하는경우 SecurityContextHolder Authentication Principal 주입받는다.
     * @param toUserId 구독자의 userId
     * @return
     */
    @PostMapping(value = "/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PathVariable("toUserId") Long toUserId){
        subscribeService.구독하기(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공",null), HttpStatus.OK);
    }

    /**
     * Path. Http://localhost:8080/api/subscribe/{userId}
     * 사용자가 구독을 취소하고자 하는 경우 사용자의 인증정보와 구독자의 userId를 이용해서 구독을 취소한다.
     * @param principalDetails 사용자가 로그인을 했을경우 SecurityContextHolder Authentication Principal 주입받는다.
     * @param toUserId
     * @return
     */
    @DeleteMapping(value = "/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @PathVariable("toUserId") Long toUserId){
        subscribeService.구독취소하기(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독취소하기 성공",null),HttpStatus.OK);
    }

}