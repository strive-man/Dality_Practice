package com.kgkt.tust.member.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zgq
 * @description 密码格式正则验证的工具类
 */
public final class PwdValidator {

  private PwdValidator() {}

  /**
   * @author zgq
   * @param parameter 要匹配的传入参数
   * @return java.lang.Boolean
   * @description
   */
  public static Boolean exam(String parameter) {
    // 这是要匹配的正则表达式
    String regexPattern =
        "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,}$";
    return validateParameter(parameter, regexPattern);
  }

  public static boolean validateParameter(String parameterValue, String regexPattern) {
    Pattern pattern = Pattern.compile(regexPattern);
    Matcher matcher = pattern.matcher(parameterValue);
    return matcher.matches();
  }
}
