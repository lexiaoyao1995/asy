package com.asy.demo.controller;

import com.asy.demo.asy.LongTimeTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AsyController {

    @Autowired
    private LongTimeTask longTimeTask;

    @GetMapping("longtask1")
    public void test1() {
        longTimeTask.task1();
        log.info("controller over");
    }

    @GetMapping("longtask2")
    public void test2() {
        longTimeTask.task2();
        log.info("controller over");

    }


}