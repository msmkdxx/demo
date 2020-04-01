package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.conf.CurrentUser;
import com.example.demo.conf.LoginReqired;
import com.example.demo.dto.Goods;
import com.example.demo.dto.LoginUser;
import com.example.demo.dto.User;
import com.example.demo.utils.IpUtils;
import com.example.demo.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
@Api(tags = "实时查看操作")
public class UserController {
    @Autowired
    private User user;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping(value = "/login")
    @ApiOperation("登录")
    public String login(@RequestParam(value = "phone")String phone,@RequestParam(value = "password")String password,
                        HttpServletRequest request){
            //获取token,token值唯一
            String token = request.getSession().getId();
            LoginUser loginUser = new LoginUser();
            loginUser.setU_id(user.getU_id());
            loginUser.setU_name(user.getU_name());
            loginUser.setU_password(user.getU_password());
            loginUser.setU_age(user.getU_age());
            //将loginUser对象转为string类型，作为token的value存入缓存
            redisUtils.set(token, JSONObject.toJSONString(loginUser));
            return token;
    }

    @GetMapping(value = "/addGoods")
    @ApiOperation("添加商品")
    @LoginReqired
    public void addGoods(@Validated Goods goods, HttpServletRequest request){
        redisUtils.set(IpUtils.getIpAddr(request),JSONObject.toJSONString(goods));
    }

    @GetMapping(value = "/getGoods")
    @ApiOperation("查询商品")
    @LoginReqired
    public Goods getGoods(HttpServletRequest request){
        Object obj = redisUtils.get(IpUtils.getIpAddr(request));
        if(!ObjectUtils.isEmpty(obj)){
            Goods goods = JSONObject.parseObject(obj.toString(),Goods.class);
            return goods;
        }
        return null;
    }

    @GetMapping(value = "/getUser")
    @ApiOperation("获取登录用户信息")
    @LoginReqired
    public LoginUser getUser(@CurrentUser LoginUser loginUser){
        if(ObjectUtils.isEmpty(loginUser)){
            return null;
        }
        return loginUser;
    }

    @GetMapping(value = "/test")
    public void test(){
        List<User> userList = new ArrayList<User>();
        userList.add(user);

        userList.stream().forEach(u -> u.getU_age());
        userList.stream().forEach(User::getU_age);

        //todo 遍历形成一个新List
        List<Integer> numsList = userList.stream().map(User::getU_age).collect(Collectors.toList());

        //todo 一句话将nums加起来
        int sum = userList.stream().mapToInt(User::getU_age).sum();

        //筛选
        userList.stream().filter(u->u.getU_age()>1).collect(Collectors.toList());

        List<User> userList1 = new ArrayList<User>();
        Assert.notEmpty(userList,"成功");
        Assert.notEmpty(userList1,"失败");
    }
}
