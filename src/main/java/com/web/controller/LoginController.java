package com.web.controller;

import com.web.entity.User;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiaoyou.wyz on 2017/7/30.
 */
@Controller("LoginController")
@RequestMapping("")
public class LoginController {
   /* 并没有在XML配置bean，用这个注解将其注解为bean，框架内部会自动将其当成一个bean，让spring容器来进行管理，
    在配置文件里面设置一个扫描，如果某一个类有这个注解，那么他会找到这个注解，内部封装成一个bean*/
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView("/login");
        String userName=request.getParameter("username");
        String password=request.getParameter("userpass");
        HttpSession session=request.getSession();
        if(userName==null||password==null){

        }else{
            if(userName!=""&&password!=""){
                // 0 :成功  -1: 用户不存在 -2:密码错误
                int flag=userService.login(userName,userName,password);
                switch (flag){
                    case 0:
                        session.setAttribute("user",userService.getUserByUsername(userName));
                        modelAndView=new ModelAndView("redirect:/p");
                        break;
                    case -1:
                        modelAndView.addObject("error","用户不存在");
                        break;
                    case -2:
                        modelAndView.addObject("error","密码错误");
                        break;
                }
            }
        }
        return  modelAndView;
    }
    @RequestMapping("/usermess")
    public  ModelAndView jumpUsermess(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/usermess");
        String username = request.getParameter("username");
        String userpass = request.getParameter("userpass");
        String useremail = request.getParameter("useremail");
        String userdesc = request.getParameter("userdesc");
        User user= (User) request.getSession().getAttribute("user");
        if(user.getUsername()!=username && username!=null && username!="") {
            user.setUsername(username);
        }
        if(user.getNickname()!=userpass && userpass!=null && userpass!="") {
            user.setNickname(userpass);
        }
        if(user.getEmail()!=useremail && useremail!=null && useremail!="") {
            user.setEmail(useremail);
        }
        if(user.getSchool()!=userdesc && userdesc!=null && userdesc!="") {
            user.setSchool(userdesc);
        }

        if(1==userService.updataUser(user))
        {
            System.out.println("更新成功");
        }

        request.getSession().setAttribute("user",user);
        return mav;
    }
}
