package com.example.demo.controller;

import com.example.demo.dto.Book;
import com.example.demo.service.BookService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.ReturnResult;
import com.example.demo.utils.ReturnResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "book操作")
@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation("添加book")
    @GetMapping(value = "/insert")
    public boolean insert(@Validated Book book){
        return bookService.insert(book);
    }

    @ApiOperation("修改book")
    @GetMapping(value = "/update")
    public boolean update(){
        return bookService.update();
    }

    @ApiOperation("删除book")
    @GetMapping(value = "/del")
    public boolean del(){
        return bookService.del();
    }


    @ApiOperation("查询book")
    @GetMapping(value = "/selectBook")
    public ReturnResult<PageUtils<List<Book>>> selectBook(@Validated PageUtils pageUtils){
        try {
            Object data  = bookService.selectBook(pageUtils);
            if (ObjectUtils.isEmpty(data)){
                return ReturnResultUtils.returnSucess();
            }else {
                return ReturnResultUtils.returnSucess(0,"成功",data);
            }
        } catch (Exception e) {
            return ReturnResultUtils.returnFail(1,"莫名其妙");
        }
    }
}
