package com.email.support.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suiguozhen
 * @date 19/07/05
 */
@Controller
@RequestMapping("test")
public class Test {

    @GetMapping
    public String index(){
        return "xxxx";
    }
}
