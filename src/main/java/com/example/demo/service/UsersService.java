package com.example.demo.service;

import com.example.demo.dto.Users;
import com.example.demo.dto.UsersExample;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.utils.SHAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 注册
     * @param users
     * @return
     */
    public boolean register(Users users) {
        try {
            //密码加密
            users.setPassword(SHAUtils.stringSha1(users.getPassword()));
            usersMapper.insertSelective(users);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
