package com.kgkt.tust.member.config;

import com.kgkt.tust.member.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zgq
 * @description SB框架下的配置类，我们定义的登录拦截器类：LoginInterceptor的实现
 */
@Configuration
public class MyLoginInterceptor implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 注册自定义拦截器LoginInterceptor
    registry
        .addInterceptor(new LoginInterceptor())
        .addPathPatterns("/login/**") // 拦截所有的请求
        // TODO 后续需要继续添加放行页面
        .excludePathPatterns(
            "/login/doLogin",
            "/login/identifyImage",
            "/login/doLogins",
            "/images/**",
            "/register/save",
            "/register/store");
  }
}
