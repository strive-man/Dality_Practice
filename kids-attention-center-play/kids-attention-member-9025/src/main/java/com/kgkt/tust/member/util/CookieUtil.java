package com.kgkt.tust.member.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author zgq
 * @description 处理cookie的一些方法
 */
@Slf4j
public class CookieUtil {

  private CookieUtil() {}

  /**
   * @param request Servlet的一个常用参数
   * @return String
   * @description 拿到注册成功狗发给前端 ey为 "userIdentify" 的 cookie值
   */
  public static String getCookieValue(HttpServletRequest request) {
    String res = "";
    Cookie[] cookies = request.getCookies();
    if (cookies.length != 0) {
      for (Cookie cookie : cookies) {
        if ("userIdentify".equals(cookie.getName())) {
          res = cookie.getValue();
        }
      }
      return res;
    }
    return null;
  }

  public static void setCookie(
      HttpServletResponse response, String cookieName, String cookieValue) {
    if (StringUtils.hasText(cookieName) && StringUtils.hasText(cookieValue)) {
      Cookie cookie = new Cookie(cookieName, cookieValue);
      response.addCookie(cookie);
    }
  }
}
