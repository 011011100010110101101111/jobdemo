package com.hzsun.jobdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : liuwenlong
 * @desc : 主controller，这个单独为显示页面
 * @date : 2019-06-27 15:46
 */
@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
