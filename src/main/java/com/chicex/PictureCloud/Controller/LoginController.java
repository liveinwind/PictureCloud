package com.chicex.PictureCloud.Controller;

import com.chicex.PictureCloud.ApplicationStartUpConfig;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.apache.shiro.SecurityUtils.getSubject;

@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){
        Subject subject = getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
        subject.login(token);}
        catch (Exception e){
            return "redirect:"+ ApplicationStartUpConfig.LoginPage;
        }
        return "redirect:"+ ApplicationStartUpConfig.MainPage;
    }

}
