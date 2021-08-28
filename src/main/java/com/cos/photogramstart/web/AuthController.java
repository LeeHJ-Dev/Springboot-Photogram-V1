package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller //1. IoC 2.파일을 리턴하는 컨트롤러
public class AuthController {

    private final AuthService authService;

    //public AuthController(AuthService authService) {
    //    this.authService = authService;
    //}

    /**
     * 사용자가 인증이 필요한 화면(Spring Security antMatcher)에 인증없이 접근하는 경우 로그인 화면경로를 리턴한다.
     * @return 사용자 로그인 화면. (Path. Http://localhost:8080/auth/signin.jsp file return)
     */
    @GetMapping(value = "/auth/signin")
    public String signinForm(){
        return "/auth/signin";
    }

    /**
     * 사용자가 인증이 필요한 화면에 접근하는경우 로그인 창을 제공하며, 회원가입이 되지 않았을 경우 회원가입 폼파일(.jsp)을 제공한다.
     * @return 회원가입 주소. (path. http://localhost:8080/auth/signup.jsp file return)
     */
    @GetMapping(value = "/auth/signup")
    public String signupForm(){
        return "/auth/signup";
    }

    /**
     * Path. http://localhost:8080/auth/signup
     * 사용자가 인스타그램 회원가입을 하기위해서 회원정보를 입력 후 저장하며 회원가입이 완료되면 로그인 페이지로 이동한다.
     * @param signupDto (SignupDto class)
     * @return 사용자가 회원가입을 진행하고, 로그인 페이지로 화면을 이동한다. (path. http://localhost:8080/auth/signin)
     */
    @PostMapping(value = "/auth/signup")
    public String signup(SignupDto signupDto){
        User user = signupDto.toEntity();
        User userEntity = authService.회원가입(user);
        return "/auth/signin";
    }
}