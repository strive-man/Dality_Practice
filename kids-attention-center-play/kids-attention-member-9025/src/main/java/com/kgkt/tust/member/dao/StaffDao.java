package com.kgkt.tust.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgkt.tust.member.entity.StaffEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zgq
 * @description MyBatisPlus框架下的Dao，不多介绍
 */
@Mapper
public interface StaffDao extends BaseMapper<StaffEntity> {

  /**
   * @param staffAccount 孩子父母的登录账号
   * @return com.kgkt.tust.member.entity.StaffEntity
   * @description 在staff表根据员工账号字段，查询整条记录
   */
  StaffEntity selectByStaffAccount(String staffAccount);
}
