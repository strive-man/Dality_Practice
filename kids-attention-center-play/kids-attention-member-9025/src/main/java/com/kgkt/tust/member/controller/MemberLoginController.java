package com.kgkt.tust.member.controller;

import com.kgkt.tust.common.utils.R;
import com.kgkt.tust.member.service.ParentService;
import com.kgkt.tust.member.service.StaffService;
import com.kgkt.tust.member.vo.ParentVo;
import com.kgkt.tust.member.vo.StaffVo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgq
 * @description Parent 和 Staff两类用户的登录接口实现
 */
@Slf4j
@RestController
@RequestMapping("/login")
@CrossOrigin
public class MemberLoginController {

  @Resource private ParentService parentService;
  @Resource private StaffService staffService;

  /**
   * @param parentVo 只有parentEntity实体类两个字段的构造类, request, response
   * @return java.lang.String
   * @description 实现登录功能-parent
   */
  @PostMapping("/doLogin")
  public R doLogin(ParentVo parentVo, HttpServletRequest request, HttpServletResponse response) {
    // 调用实现登录功能的方法：doLogin
    return parentService.doLogin(parentVo, request, response);
  }

  /**
   * @param staffVo 只有staffEntity实体类两个字段的构造类, request, response
   * @return java.lang.String
   * @description 实现登录功能-staff
   */
  @PostMapping("/doLogins")
  public R doLogins(StaffVo staffVo, HttpServletRequest request, HttpServletResponse response) {
    return staffService.doLogins(staffVo, request, response);
  }
}
