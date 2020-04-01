package com.example.demo.controller;

import com.example.demo.dto.Users;
import com.example.demo.enums.ErrorEnums;
import com.example.demo.enums.ErrorType;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.UsersException;
import com.example.demo.service.UsersService;
import com.example.demo.utils.ReturnResult;
import com.example.demo.utils.ReturnResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户")
@RestController
@RequestMapping(value = "/users")
@Log4j
public class UsersController {
    //private final static Logger logger = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "注册")
    @GetMapping(value = "/register")
    public ReturnResult register(@Validated Users users) {
        boolean isRegister = usersService.register(users);
        if (isRegister) return ReturnResultUtils.returnSucess();

        return ReturnResultUtils.returnFail(1, "注册失败");
    }

    @GetMapping(value = "/test")
    public ReturnResult test() {
        try {
            log.info("ok");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("错误");
            throw new UsersException(ErrorEnums.ERROR_PARAM);
        }
        return ReturnResultUtils.returnSucess();
    }

    @GetMapping(value = "/test1")
    public ReturnResult test1() {
        return ReturnResultUtils.returnFail(ErrorType.OBJ_NOT_FOUND.getCode(),ErrorType.OBJ_NOT_FOUND.getMessage());
    }

}
