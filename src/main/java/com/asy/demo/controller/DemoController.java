package com.asy.demo.controller;

import com.asy.demo.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class DemoController {


    @Autowired
    private AppService appService;

    @GetMapping("task")
    public String task1() {
        log.info("Request received");
        String s = appService.execute();
        log.info("Servlet thread released");
        return s;
    }

    @GetMapping("task_call")
    public Callable task2() {
        log.info("Request received");
        Callable<String> callable = appService::execute;
        log.info("Servlet thread released");
        return callable;
    }

    @GetMapping("task_deferredResult")
    public DeferredResult<String> task3() {
        log.info("Request received");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(appService::execute).whenCompleteAsync((success, err) -> {
            deferredResult.setResult(success);
        });
        log.info("Servlet thread released");
        return deferredResult;
    }

}