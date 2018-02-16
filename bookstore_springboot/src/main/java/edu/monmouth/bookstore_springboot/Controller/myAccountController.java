package edu.monmouth.bookstore_springboot.Controller;


import Utils.ImageCode;
import edu.monmouth.bookstore_springboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

@Controller
public class myAccountController {
    @RequestMapping("myAccount.do")
    public String checkUser(HttpSession session){
     User user = (User) session.getAttribute("user");
     if(user==null){
         System.out.println("has been used");
         return "login";
     }
     else {

         return "myAccount";
     }
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping("myAccount")
    public String myaccount(){
        return "myAccount";
    }
    @RequestMapping("register.do")
    public String Register(){
        return "register";
    }
    @RequestMapping("imagecode")
    public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutputStream os = response.getOutputStream();
        Map<String,Object> map = ImageCode.getImageCode(60, 20, os);
        String simpleCaptcha = "simpleCaptcha";
        request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime",new Date().getTime());
        try {
            ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
        } catch (IOException e) {
            return "";
        }
        return null;
    }


}
