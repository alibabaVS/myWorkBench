//package com.example.ribbonconsumer;
//
//import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
///**
// * PackageName:com.example.ribbonconsumer
// * ClassName: RequestCacheFilter
// *
// * @author zha.jiangjiang
// * @Description:
// * @Date: 2018/9/20 21:10
// * @see
// * @since JDK 1.8
// */
//@WebFilter(urlPatterns = "/*")
//public class RequestCacheFilter implements Filter {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("初始化filter");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("执行filter");
//        HystrixRequestContext context = HystrixRequestContext.initializeContext();
//        try {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            context.shutdown();
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
