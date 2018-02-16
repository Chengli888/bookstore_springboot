package edu.monmouth.bookstore_springboot.Controller;

import edu.monmouth.bookstore_springboot.Dao.UserDao;
import edu.monmouth.bookstore_springboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserController {
    @Resource
    UserDao userDao;
    @RequestMapping("User_Register.do")
    public String UserRegisteration(HttpServletRequest request, HttpSession session, User user){
        System.out.println(user+"-----------------------user");
        String checkCode = request.getParameter("checkCode");
        Object cko = session.getAttribute("simpleCaptcha") ; //验证码对象
        if(cko == null){
            request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
        }
        String captcha = cko.toString();
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime")+"");
        if(StringUtils.isEmpty(checkCode) || captcha == null ||  !(checkCode.equalsIgnoreCase(captcha))) {
            request.setAttribute("errorMsg", "Verification code error!");
            return "register";

        } else if ((now.getTime()-codeTime)/1000/60>5) {
            //验证码有效时长为5分钟
            request.setAttribute("errorMsg", "Verification code is invalid, please re-enter!");
            return "register";
        }else {
            user.setActivecode("0");
            user.setRegistTime(now);
            user.setRole("common");
            userDao.save(user);
            session.removeAttribute("simpleCaptcha");
            return "registersuccess";
        }

    }
    @RequestMapping("login.do")
    public String login(@RequestParam( "username") String username,@RequestParam("password") String password,HttpSession session){
        User user = (User) userDao.findByUsernameAndPassword(username,password);
        session.setAttribute("no_pay","no_pay");
        session.setAttribute("have_pay","have_pay");
        if(user == null){
            session.setAttribute("user_msg","error username or password");
            return "login";
        }else {
            session.setAttribute("user",user);
            if("admin".equals(user.getRole())){
                return "redirect:/admin/login/home.jsp";
            }else {
                user.setActivecode("1");
                userDao.save(user);
                return "index";
            }
        }

    }
    @RequestMapping("ModifyUserInfo")
    public String ModifyUserInfoview(){

        return "modifyuserinfo";
    }
    @RequestMapping("ModifyUserInfo.do")
    public String ModifyUserInfo(User user,HttpSession session){
        User user1 = (User) session.getAttribute("user");
        userDao.updateUser(user.getPassword(),user.getGender(),user.getTelephone(),user1.getId());
//       User user1 = (User) session.getAttribute("user");
//       user1.setPassword(user.getPassword());
//       user1.setTelephone(user.getTelephone());
//       user1.setGender(user.getGender());
//       session.setAttribute("user",user1);
//        session.removeAttribute("user");
        session.invalidate();
       return "modifyUserInfoSuccess";
    }
    @RequestMapping("logout.do")
    public String logout(HttpSession Session){
        Session.invalidate();
        return "login";
    }
}
