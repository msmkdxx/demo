package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginUser {
    private long u_id;
    private String u_name;
    private String u_password;
    private int u_age;
}
