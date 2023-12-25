package cn.realtime.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String sayHello(){
        return "你好";
    }

}
