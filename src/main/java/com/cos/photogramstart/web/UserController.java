package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    /**
     * Path. Http://localhost:8080/user/profile
     * @return
     */
    @GetMapping(value = "/user/{id}")
    public String profile(@PathVariable("id") Long id){
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
