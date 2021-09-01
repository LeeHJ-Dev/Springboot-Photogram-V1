package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    /**
     * Path. Http://localhost:8080/user/{id}
     * @return
     */
    @GetMapping(value = "/user/{id}")
    public String profile(@PathVariable("id") Long id, Model model){
        User userEntity = userService.회원프로필(id);
        model.addAttribute("user",userEntity);
        return "/user/profile";
    }

    /**
     * Path. Http://localhost:8080/user/{id}/update
     * @return
     */
    @GetMapping(value = "/user/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails){
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principal = (PrincipalDetails)authentication.getPrincipal();
        */

        // WEB-INF/views/layout/header.jsp principal value setting.
        //model.addAttribute("principal",principalDetails.getUser());
        return "/user/update";
    }
}
