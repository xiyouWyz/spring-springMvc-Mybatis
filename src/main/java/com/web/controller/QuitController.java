package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiaoyou.wyz on 2017/7/31.
 */
@Controller("QuitController")
@RequestMapping("/quit")
public class QuitController {
    @RequestMapping("")
    public ModelAndView quit(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView=null;
        HttpSession session=request.getSession();
        session.invalidate();
        modelAndView=new ModelAndView("redirect:/login");
        return  modelAndView;
    }
}
