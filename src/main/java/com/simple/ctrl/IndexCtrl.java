package com.simple.ctrl;

import com.simple.common.annotation.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName IndexCtrl.java
 * @Description TODO
 * @createTime 2021年05月27日 09:39:00
 */
@RestController
@ResponseResult
public class IndexCtrl {

    @GetMapping("/")
    public String index(){

        return "{file:-1}";
    }

}
