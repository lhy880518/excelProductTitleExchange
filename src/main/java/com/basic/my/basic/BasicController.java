package com.basic.my.basic;

import com.basic.my.basic.util.CacheHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

    static Logger log = LoggerFactory.getLogger(BasicController.class);

    @RequestMapping("/")
    public String mainPage(ModelMap modelMap){
        log.debug("cacheHandler.getCategoryCache()");
        modelMap.addAttribute("ko","한글");
        return "/main";
    }
}
