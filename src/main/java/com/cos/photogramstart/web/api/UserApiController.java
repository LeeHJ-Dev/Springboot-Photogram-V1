package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping(value = "/api/user/{id}")
    public CMRespDto<?> update(@PathVariable("id") Long id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult, // 꼭 @valid가 적혀있는 다음 parameter 적어야 함
                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(bindingResult.hasErrors()){
            Map<String, String> errMap = new HashMap<>();
            for(FieldError fieldError : bindingResult.getFieldErrors()){
                errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성검사실패",errMap);
        }else{}

        User userEntity = userService.회원수정(id,userUpdateDto);
        principalDetails.setUser(userEntity);
        return new CMRespDto<User>(1,"회원수정완료",userEntity);
    }


}
