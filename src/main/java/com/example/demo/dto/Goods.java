package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Goods {
    @ApiModelProperty(value = "商品id",required = true)
    private long g_id;
    @ApiModelProperty(value = "商品名",required = true)
    private String g_name;
    @ApiModelProperty(value = "商品价格",required = true)
    private double g_price;
    @ApiModelProperty(value = "商品数量",required = true)
    private int g_num;
    @ApiModelProperty(value = "商品五指图",required = true)
    private String g_img;
}
