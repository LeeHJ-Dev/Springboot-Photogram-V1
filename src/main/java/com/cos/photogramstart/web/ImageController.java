package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    /**
     * Path. Http://localhost:8080/ or Http://localhost:8080/image/story
     * @return
     */
    @GetMapping(value = {"/","/image/story"})
    public String story(){
        return "/image/story";
    }

    /**
     * Path. Http://localhost:8080/image/popular
     * @return
     */
    @GetMapping(value = "/image/popular")
    public String popular(){
        return "/image/popular";
    }

    /**
     * Path. Http://localhost:8080/image/upload
     * @return
     */
    @GetMapping(value = "/image/upload")
    public String upload(){
        return "/image/upload";
    }

}
