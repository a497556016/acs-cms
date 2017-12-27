package com.henede.admin.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web/index")
public class IndexController {

    @RequestMapping({"/","/**"})
    public String index(HttpServletRequest request, ModelMap model){
        model.addAttribute("name","Heshaowei");
        return "index";
    }
}
