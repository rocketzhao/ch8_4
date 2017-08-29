package com.wisely.ch8_4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * springMVC
 *
 * Created by jun.zhao on 2017/8/29.
 */
@Controller
public class Ch84Controller {

    @ResponseBody
    @RequestMapping(value = "/")
    public String index(){
        return "Hello World!";
    }

    @RequestMapping(value = "welcome")
    public String welcome(){
        return "welcome";
    }

    @ResponseBody
    @RequestMapping(value = "testChinese")
    public String testChinese(String content){
        return content;
    }

}
