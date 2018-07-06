package com.style.slack.controller.douban;


import com.style.slack.utils.HttpClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    //https://api.douban.com/v2/movie/$(board.key}?start=0&count=10
    @ApiOperation(value="多参数查询信息")
    @ResponseBody
    @GetMapping("/getMultiInfo")
    public Object getMultiInfo( @ApiParam(value="参数1") @RequestParam String param1,
                                @ApiParam(value="参数2") @RequestParam String param2,
                                @ApiParam(value="参数3") @RequestParam String param3){

        String baseUrl = "https://api.douban.com/v2/movie/";
        String endUrl = "?start=0&count=10";
        Map resultMap = new HashMap();
        if(!StringUtils.isEmpty(param1)){
            String url = baseUrl+param1+endUrl;

            Object result = HttpClientUtils.httpGet(url);
            resultMap.put(param1,result);
        }
        if(!StringUtils.isEmpty(param2)){
            String url = baseUrl+param2+endUrl;

            Object result = HttpClientUtils.httpGet(url);
            resultMap.put(param2,result);
        }
        if(!StringUtils.isEmpty(param3)){
            String url = baseUrl+param3+endUrl;

            Object result = HttpClientUtils.httpGet(url);
            resultMap.put(param3,result);
        }

        return resultMap;
    }


   // https://api.douban.com/v2/movie/subject/26752088

    @ApiOperation(value="根据ID查询电影信息")
    @ResponseBody
    @GetMapping("/getMovieInfoById")
    public Object getMovieInfoById(  @ApiParam(value="电影id") @RequestParam String id){
        Object result = HttpClientUtils.httpGet("https://api.douban.com/v2/movie/subject/"+id);
        return result;
    }

}
