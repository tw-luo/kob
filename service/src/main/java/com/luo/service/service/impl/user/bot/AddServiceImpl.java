package com.luo.service.service.impl.user.bot;

import com.luo.service.mapper.BotMapper;
import com.luo.service.pojo.Bot;
import com.luo.service.pojo.User;
import com.luo.service.service.impl.utils.UserDetailsImpl;
import com.luo.service.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    private BotMapper botMapper;
    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        String bot_name=data.get("bot_name");
        String description=data.get("description");
        String content=data.get("content");

        Map<String,String> map=new HashMap<>();

        if(bot_name==null||bot_name.equals("")){
            map.put("message","bot的名字不能为空");
            return map;
        }

        if(description==null||description.equals("")){
            description="这个用户很懒，什么都没有写。";
        }

        if(description.length()>300){
            map.put("message","描述不能超过300个字符");
            return map;
        }

        if(content.length()>10000){
            map.put("message","内容不能超过10000个字符");
            return map;
        }

        Date currentTime = new Date();
        Bot bot=new Bot(null,user.getId(),bot_name,description,content,1500,currentTime,currentTime);
        botMapper.insert(bot);

        return map;
    }
}
