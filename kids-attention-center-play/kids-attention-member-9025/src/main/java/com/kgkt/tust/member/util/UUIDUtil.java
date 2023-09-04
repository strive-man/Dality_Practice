package com.kgkt.tust.member.util;

import java.util.UUID;

/**
 * @author zgq
 * @description 生成一个不带 “-”的 uuid工具类
 */
public final class UUIDUtil {

  private UUIDUtil() {}

  public static String uuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
