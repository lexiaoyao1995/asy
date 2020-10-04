package com.asy.demo.controller;

import com.asy.demo.async.DeferredResultHolder;
import com.asy.demo.queue.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Slf4j
public class AppController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * 只需要创建DeferredResult
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("order")
    public DeferredResult<String> get() throws InterruptedException {
        String orderNum = "xxx";
        mockQueue.setPlaceOrder(orderNum);
        //10s超时
        DeferredResult<String> result = new DeferredResult<>(10 * 1000L);
        deferredResultHolder.getMap().put(orderNum, result);
        return result;
    }

}