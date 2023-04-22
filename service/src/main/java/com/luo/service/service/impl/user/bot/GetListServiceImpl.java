package com.luo.service.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luo.service.pojo.Bot;
import com.luo.service.pojo.User;
import com.luo.service.service.impl.utils.UserDetailsImpl;
import com.luo.service.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.luo.service.mapper.BotMapper;

import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public List<Bot> getList() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Bot> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());

        return botMapper.selectList(wrapper);
    }
}
