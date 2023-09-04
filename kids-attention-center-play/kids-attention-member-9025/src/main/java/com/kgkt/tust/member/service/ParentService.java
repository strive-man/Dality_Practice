package com.kgkt.tust.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgkt.tust.common.utils.PageUtils;
import com.kgkt.tust.common.utils.R;
import com.kgkt.tust.member.entity.ParentEntity;
import com.kgkt.tust.member.vo.ParentVo;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zgq
 * @description MyBatisPlus框架下的Service层，不多介绍
 */
public interface ParentService extends IService<ParentEntity> {

  PageUtils queryPage(Map<String, Object> params);

  R doLogin(ParentVo parentVo, HttpServletRequest request, HttpServletResponse response);

  R alterParent(ParentVo parentVo);
}
