package com.luo.service.controller.user;

import com.luo.service.mapper.UserMapper;
import com.luo.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @RequestMapping("all")
    public List<User> GetAllUser() {
        return userMapper.selectList(null);
    }

    @RequestMapping("{user_id}")
    public User getUser(@PathVariable Integer user_id) {
        User user = userMapper.selectById(user_id);
        return user;
    }

    @RequestMapping("add/{user_id}/{username}/{password}")
    public String addUser(@PathVariable Integer user_id,@PathVariable String username, @PathVariable String password) {
        if(userMapper.selectById(user_id)!=null){
            return "user already exist";
        }
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder() ;

        password=passwordEncoder.encode(password);
        User user = new User(user_id,username,password,1500);
        userMapper.insert(user);
        return "success";
    }

    @RequestMapping("remove/{user_id}")
    public String removeUser(@PathVariable Integer user_id) {
        User user= userMapper.selectById(user_id);
        if(user==null){
            return "user not exist";
        }
        userMapper.deleteById(user_id);
        return "success";
    }
}
