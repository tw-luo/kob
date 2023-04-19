package com.luo.service.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luo.service.mapper.UserMapper;
import com.luo.service.pojo.User;
import com.luo.service.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmPassword) {
        Map<String,String> resp=new HashMap<>();

        if(username==null){
            resp.put("message","用户名不能为空");
            return resp;
        }

        username=username.trim();

        if(username.length()==0){
            resp.put("message","用户名不能为空");
            return resp;
        }

        if(password==null||password.length()==0){
            resp.put("message","密码不能为空");
            return resp;
        }

        if(confirmPassword==null||confirmPassword.length()==0){
            resp.put("message","确认密码不能为空");
            return resp;
        }

        if(username.length()>20){
            resp.put("message","用户名长度不能超过20");
            return resp;
        }

        if(password.length()<6||password.length()>20||confirmPassword.length()>20||confirmPassword.length()<6){
            resp.put("message","密码长度必须在6-20之间");
            return resp;
        }

        if(!password.equals(confirmPassword)){
            resp.put("message","两次密码不一致");
            return resp;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            resp.put("message", "用户名已存在");
            return resp;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/8772_lg_fd9f421258.jpeg";
        User user = new User(null, username, encodedPassword, photo);
        userMapper.insert(user);

        resp.put("message", "success");

        return resp;
    }
}
