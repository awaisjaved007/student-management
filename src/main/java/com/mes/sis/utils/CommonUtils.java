package com.mes.sis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

  private static Logger LGR = LoggerFactory.getLogger(CommonUtils.class);

  public static String concat(String... args) {

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      builder.append(args[i]);
    }
    return builder.toString();
  }

  public static void debug(Logger log,String ... messages) {
      log.debug(log.isDebugEnabled() ? CommonUtils.concat(messages):null);

  }
  public static void warn(Logger log,String ... messages) {
    log.warn(log.isWarnEnabled() ? CommonUtils.concat(messages):null);

  }
}
