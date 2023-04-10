package com.luo.service.controller.pk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pk/")
public class StaticController {
    @RequestMapping("index")
    public String index() {
        return "pk/index";
    }
}
