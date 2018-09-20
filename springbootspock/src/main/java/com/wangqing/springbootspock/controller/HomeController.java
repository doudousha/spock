package com.wangqing.springbootspock.controller;

import com.wangqing.springbootspock.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.SQLOutput;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("home")
    @ResponseBody
    public String home(){

        System.out.println("hello world");

        return   "home";
    }

}
