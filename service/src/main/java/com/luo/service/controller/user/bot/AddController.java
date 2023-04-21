package com.luo.service.controller.user.bot;

import com.luo.service.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddController {

    @Autowired
    private AddService addService;

    @RequestMapping("/user/bot/add")
    public Map<String,String> add(@RequestParam Map<String,String> map) {

        return addService.add(map);
    }

}
