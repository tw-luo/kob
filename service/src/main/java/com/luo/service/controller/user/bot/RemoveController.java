package com.luo.service.controller.user.bot;

import com.luo.service.service.impl.user.bot.RemoveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveController {
    @Autowired
    private RemoveServiceImpl removeService;

    @PostMapping("/user/bot/remove")
    public Map<String,String> remove(@RequestParam Map<String,String> map) {
        return removeService.remove(map);
    }


}
