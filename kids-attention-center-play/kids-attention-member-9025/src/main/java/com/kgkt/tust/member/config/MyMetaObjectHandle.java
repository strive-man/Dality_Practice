package com.kgkt.tust.member.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

/**
 * @author zgq
 * @description SB框架下的配置类，实现MyBatisPlus框架的字段自动填充功能
 */
@Slf4j
@Configuration
public class MyMetaObjectHandle implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("进入字段创建填充");
    this.setFieldValByName("addTime", LocalDateTime.now(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("进入字段修改填充");
    this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
  }
}
