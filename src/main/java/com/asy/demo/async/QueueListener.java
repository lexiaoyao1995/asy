package com.asy.demo.async;

import com.asy.demo.queue.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 监听spring初始化完毕后的事件
 * ApplicationListener<ContextRefreshedEvent>
 */
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;


    /**
     * 监听来DeferredResult中setResult，setResult后主线程完成相应
     *
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(() -> {
            while (true) {
                if (!StringUtils.isEmpty(mockQueue.getCompleteOrder())) {
                    String orderNum = mockQueue.getCompleteOrder();
                    log.info("放回订单处理结果" + orderNum);
                    deferredResultHolder.getMap().get(orderNum).setResult("place hold success");
                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}