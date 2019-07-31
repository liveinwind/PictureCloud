package com.chicex.PictureCloud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RedirectController {
    @RequestMapping("/")
    private String MainPage(){
        return "redirect:/management.html";
    }
}
