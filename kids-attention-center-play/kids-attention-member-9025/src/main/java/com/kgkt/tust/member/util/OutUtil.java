package com.kgkt.tust.member.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutUtil {
  private static final Logger logger = LoggerFactory.getLogger(OutUtil.class);

  private OutUtil() {}

  public static void getInfo(Exception e) {
    StackTraceElement[] stackTrace = e.getStackTrace();
    for (StackTraceElement element : stackTrace) {
      logger.info(
          "at "
              + element.getClassName()
              + "."
              + element.getMethodName()
              + "("
              + element.getFileName()
              + ":"
              + element.getLineNumber()
              + ")",
          e.getCause());
    }
  }
}
