package com.luo.service.service.impl.user.bot;

import com.luo.service.mapper.BotMapper;
import com.luo.service.pojo.Bot;
import com.luo.service.pojo.User;
import com.luo.service.service.impl.utils.UserDetailsImpl;
import com.luo.service.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authentication =
            (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Integer botId = Integer.parseInt(data.get("bot_id"));

        Bot bot=botMapper.selectById(botId);

        Map<String,String> map=new HashMap<>();

        if(bot==null){
            map.put("message","该bot不存在或已被删除");
            return map;
        }

        if(!bot.getUserId().equals(user.getId())){
            map.put("message","你没有权限删除该bot");
            return map;
        }

        botMapper.deleteById(botId);
        map.put("message","success");

        return map;
    }
}
