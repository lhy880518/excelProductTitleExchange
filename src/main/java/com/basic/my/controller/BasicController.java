package com.basic.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {
    @RequestMapping("/")
    public String mainPage(ModelMap modelMap){
        modelMap.addAttribute("ko","한글");
        return "/main";
    }
}
