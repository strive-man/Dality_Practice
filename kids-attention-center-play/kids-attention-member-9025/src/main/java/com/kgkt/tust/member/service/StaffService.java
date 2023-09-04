package com.kgkt.tust.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgkt.tust.common.utils.PageUtils;
import com.kgkt.tust.common.utils.R;
import com.kgkt.tust.member.entity.StaffEntity;
import com.kgkt.tust.member.vo.StaffVo;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zgq
 * @description MyBatisPlus框架下的Service层，不多介绍
 */
public interface StaffService extends IService<StaffEntity> {

  PageUtils queryPage(Map<String, Object> params);

  R doLogins(StaffVo staffVo, HttpServletRequest request, HttpServletResponse response);

  R alterStaff(StaffVo staffVo);
}
