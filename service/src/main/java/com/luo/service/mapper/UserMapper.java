package com.luo.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luo.service.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
