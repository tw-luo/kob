package com.luo.service.controller.user.bot;

import com.luo.service.service.impl.user.bot.UpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateController {

    @Autowired
    private UpdateServiceImpl updateService;

    @PostMapping("/user/bot/update")
    public Map<String,String> update(@RequestParam Map<String,String> map) {
        return updateService.update(map);
    }

}
