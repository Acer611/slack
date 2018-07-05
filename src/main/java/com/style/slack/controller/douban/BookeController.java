package com.style.slack.controller.douban;


import com.style.slack.utils.HttpClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags="豆瓣API调用")
@Controller
@RequestMapping("/douban")
public class BookeController {



    @ApiOperation(value="查询图书id 为1220562的图书信息")
    @ResponseBody
    @GetMapping("/getBookeInfoById")
    public Object getBoookInfo(){
        Object result = HttpClientUtils.httpGet("https://api.douban.com/v2/book/1220562");
        return result;
    }


   // https://api.douban.com/v2/movie/coming_soon?start=0&count=3

    @ApiOperation(value="查询电影信息")
    @ResponseBody
    @GetMapping("/getMovieInfo")
    public Object getMovieInfo(){
        Object result = HttpClientUtils.httpGet("https://api.douban.com/v2/movie/coming_soon?start=0&count=3");
        return result;
    }

}
