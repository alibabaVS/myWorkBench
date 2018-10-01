package com.example.ribbonconsumer;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * PackageName:com.example.ribbonconsumer
 * ClassName: ComsumerController
 *
 * @author zha.jiangjiang
 * @Description:
 * @Date: 2018/9/16 22:49
 * @see
 * @since JDK 1.8
 */
@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    RestTemplate restTemplate;

    @Resource
    HelloService helloService;


    @RequestMapping("/ribbonConsumer")
    public String helloConsumer(){
//        String string = restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
//        HystrixRequestContext.initializeContext();

        String string = helloService.helloService("123");
//        logger.info("op=start_helloConsumer1,{} ",helloService.helloService("123") );
//        logger.info("op=start_helloConsumer2, {}",helloService.helloService("123") );
//        logger.info("op=start_helloConsumer3, {}",helloService.helloService("123") );
//        logger.info("op=start_helloConsumer4, {}",helloService.helloService("1") );
//        logger.info("op=start_helloConsumer5, {}",helloService.helloService("1") );
        return string;
    }
}
