package com.luo.service.service.impl.user.bot;

import com.luo.service.mapper.BotMapper;
import com.luo.service.pojo.Bot;
import com.luo.service.pojo.User;
import com.luo.service.service.impl.utils.UserDetailsImpl;
import com.luo.service.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Integer botId=Integer.parseInt(data.get("bot_id"));

        Bot bot=botMapper.selectById(botId);

        Map<String,String> map=new HashMap<>();

        if(bot==null){
            //TODO
            map.put("message","该bot不存在或已被删除");
            return map;
        }

        if(!bot.getUserId().equals(user.getId())){
            //TODO
            map.put("message","你没有权限修改该bot");
            return map;
        }

        String botName=data.get("bot_name");
        String description=data.get("description");
        String content=data.get("content");

        if(botName==null||botName.equals("")){
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
        Bot newBot=new Bot(
                botId,
                bot.getUserId(),
                botName,
                description,
                content,
                bot.getRating(),
                bot.getCreateTime(),
                currentTime
        );

        botMapper.updateById(newBot);

        map.put("message","success");

        return map;
    }
}
