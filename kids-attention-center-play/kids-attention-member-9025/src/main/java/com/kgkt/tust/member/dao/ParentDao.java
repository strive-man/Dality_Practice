package com.kgkt.tust.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgkt.tust.member.entity.ParentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zgq
 * @description MyBatisPlus框架下的Dao，不多介绍
 */
@Mapper
public interface ParentDao extends BaseMapper<ParentEntity> {

  /**
   * @param parentAccount 孩子父母的登录账号
   * @return com.kgkt.tust.member.entity.ParentEntity
   * @description 在parent表根据家长账号字段，查询整条记录
   */
  ParentEntity selectByParentAccount(String parentAccount);
}
