package com.example.demo.conf;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.LoginUser;
import com.example.demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoginRequiredComplete implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果方法上没有添加@LoginRequired注解就不需要登录可以访问的接口 也就意味着不要进入这个方法中来
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //判断接口是否需要登录
        LoginReqired annotation = method.getAnnotation(LoginReqired.class);
        if(null!=annotation){
            //从前端请求头获取token
            String token = request.getHeader("token");
            if(!StringUtils.isEmpty(token)){
                //根据前端获取的token去redis中查找是否有value
                String tokenUser =(String) redisUtils.get(token);
                if(ObjectUtils.isEmpty(tokenUser)){
                    throw new RuntimeException("login error!");
                }else {
                    LoginUser loginUser = JSONObject.parseObject(tokenUser, LoginUser.class);
                    request.setAttribute("loginUser",loginUser);
                }
            }else {
                throw new RuntimeException("login error!");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
