package com.kgkt.tust.member.interceptor;

import com.kgkt.tust.member.entity.StaffEntity;
import com.kgkt.tust.member.util.CookieUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author zgq
 * @description 针对项目登录功能的拦截器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {
    // 从前端获取需要的参数信息
    String requestURI = request.getRequestURI();
    String account = request.getParameter("account");
    String password = request.getParameter("password");
    log.info("拦截页面的URI={}", requestURI);
    // 通过从前端拿到的cookie值，先确定是员工类用户还是家长类用户，员工类的话需要验证是否是超级管理员
    String access = CookieUtil.getCookieValue(request);
    Object entity = request.getSession().getAttribute(access);
    // 确定有用户登录
    if (StringUtils.hasText(access) && null != entity) {
      // 条件判断 ：不是超级管理员且是员工类的登录且访问没权限的个别页面，就做拦截处理：维持原页面不动
      // TODO 下面的代码要根据后续开发完善目前不影响此模块该有的功能，这里的密码加密加密处理也还没做
      if ((entity instanceof StaffEntity)
          && (!"kap".equals(account) || !"123456".equals(password))
          && "/xx/xx".equals(requestURI)) {
        // 请求转发到定位到当前页面的页面，也即是维持原页面不动
        request.getRequestDispatcher(request.getHeader("Referer")).forward(request, response);
      }
      // 相应的：家长用户登录或者员工用户登录不访问个别需要权限页面，或者超级管理员登录：直接放行
      return true;
    }
    // 用户没登录，直接返回登录页面
    request.getRequestDispatcher("/login/toLogin").forward(request, response);
    return false;
  }
}
