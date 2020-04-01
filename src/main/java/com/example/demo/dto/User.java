package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "user")
@Component
public class User {
    @ApiModelProperty(value = "用户id",required = true)
    private long u_id;
    @ApiModelProperty(value = "用户名",required = true)
    private String u_name;
    @ApiModelProperty(value = "用户密码",required = true)
    private String u_password;
    @ApiModelProperty(value = "用户年龄",required = true)
    private int u_age;
}
