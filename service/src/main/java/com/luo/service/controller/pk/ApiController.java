package com.luo.service.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pk/api/")
public class ApiController {
    @RequestMapping("get_bot_info")
    public Map<String,String> GetBotInfo() {
        Map<String,String> response = new HashMap<String,String>();
        response.put("name", "Luo");
        response.put("level", "1");
        return response;
    }
}
