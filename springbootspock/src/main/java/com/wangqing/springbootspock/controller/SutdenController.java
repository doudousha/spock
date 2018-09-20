package com.wangqing.springbootspock.controller;

import com.wangqing.springbootspock.bean.Student;
import com.wangqing.springbootspock.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLOutput;

@Controller
@RequestMapping("/student")
public class SutdenController {


    @Autowired
    private StudentValidator studentValidator;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@Valid Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        return "ok";
    }

    // 如果实现了Validator 的验证器，需要在binder 里面添加自定义的验证其
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //这个方法加载验证器，判断请求过来的要验证的对象，加载相对于的验证器。此方法是根据请求加载的，即n次请求就加载n次该方法
        if (studentValidator.supports(binder.getTarget().getClass())
                && studentValidator.getClass().getName().contains("org.springframework") == false) {
            binder.addValidators(studentValidator);
        }
        System.out.print("initBinder-----------------------------" + studentValidator.getClass().getName());
    }

    @RequestMapping(value = "save2", method = RequestMethod.POST)
    @ResponseBody
    public String save2(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        return "ok";
    }


    @RequestMapping(value = "save3", method = RequestMethod.POST)
    @ResponseBody
    public String save3(@Valid @RequestBody Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        return "ok";
    }

    /**
     * RequestBody 作用于contentType=application/json 类型数据格式
     * ModelAttribute 作用于contentType=x-www-form-urlencoded
     */
}
