package com.hb.controller;

import com.hb.util.HBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanglei
 * @date 2021/10/31 1:08 上午
 */
@RestController
public class HelloController {

    @Autowired
    private HBaseUtil hbaseUtils;

    @GetMapping("/test")
    public void test() {

        System.out.println("---判断user表是否存在---");
        Boolean t = hbaseUtils.isExists("user");
        System.out.println(t);

    }
}
