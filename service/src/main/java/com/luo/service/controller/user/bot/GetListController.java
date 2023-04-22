package com.luo.service.controller.user.bot;

import com.luo.service.pojo.Bot;
import com.luo.service.service.impl.user.bot.GetListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetListController {

    @Autowired
    private GetListServiceImpl getListService;

    @RequestMapping("/user/bot/get_list")
    public List<Bot> getList() {
        return getListService.getList();
    }

}
