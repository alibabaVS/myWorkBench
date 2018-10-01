package com.example.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * PackageName:com.example.ribbonconsumer
 * ClassName: HelloService
 *
 * @author zha.jiangjiang
 * @Description:
 * @Date: 2018/9/17 19:51
 * @see
 * @since JDK 1.8
 */
@Service
public class HelloService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    RestTemplate restTemplate;

//    经测试必须要有scope,collapser才有用，不然报错
//    经测试timerDelayInMilliseconds 属性没有用，得在application.properties里面用hystrix.collapser.default.timerDelayInMilliseconds才有用,再测试有效，需要重启，jrebel热部署不起作用
//    使用请求合并器后此方法体内的代码将不会被执行
    @HystrixCollapser(batchMethod = "helloServices",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
//    @CacheResult(cacheKeyMethod = "getCacheKey")
//    默认方法参数将作为缓存的key执行
//* 也可通过String queryUser(@CacheKey("id") String id) 这样设置，而且用@CacheKey会报错，问题还未解决
//* 也可通过@CacheResult(cacheKeyMethod="getKeyMethod")优先级比@CacheKey("id")高

//    hystrix默认超时时间 教程说是2000ms，测试好像是1000
//    HystrixCommand用在依赖的服务返回单个操作结果的时候
//    HystrixObservableCommand用在依赖的服务返回多个操作结果的时候
//    @HystrixCommand(fallbackMethod="helloFallback",commandProperties={@HystrixProperty(name = "requestCache.enabled", value = "true")})
    public String helloService(String id){
        Long startTime = System.currentTimeMillis();
//        String string = restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class,id).getBody();
        String string = "!@#";
        Long endTime = System.currentTimeMillis();
        logger.info("spendTime ={}>>>>>>>>>>>>>>>>>>>！！！！！！！>>>>>>>>>, ",endTime-startTime );
        return string;
    }

//    @HystrixCollapser(batchMethod = "helloService",collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
    @HystrixCommand(fallbackMethod="helloFallback",commandProperties={@HystrixProperty(name = "requestCache.enabled", value = "true")})
    public List<String> helloServices(List<String> id){
        logger.info("op=start_helloServices, id={}>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", id);
        Long startTime = System.currentTimeMillis();
        List<String> string = restTemplate.getForEntity("http://HELLO-SERVICE/hello2?ids={1}",List.class,StringUtils.join(id)).getBody();
//        List<String> string = restTemplate.getForEntity("http://HELLO-SERVICE/hello2?ids={1}",List.class,id).getBody();
        logger.info("op=start_helloServices, string={}>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", string);
        Long endTime = System.currentTimeMillis();
        logger.info("spendTime ={}>>>>>>>>>>>>>>>>>>>>>>>>>>>>, ",endTime-startTime );
        return string;
    }

    public List<String> helloFallback(List<String> ids,Throwable throwable){
        logger.info("op=start_helloFallback, throwable={}>>>>>>>>>>>>>>>>>>>>>>>>>>>>", throwable);
        List<String> list = new ArrayList<>();
        list.add("error");
//        return "error";
        return list;
    }
    public String getCacheKey(String id){
        logger.info("op=getCacheKey, throwable={}",id);
        return id;
    }
}
