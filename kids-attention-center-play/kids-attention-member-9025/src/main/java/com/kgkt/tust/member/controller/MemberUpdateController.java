package com.kgkt.tust.member.controller;

import com.kgkt.tust.common.utils.R;
import com.kgkt.tust.member.service.ParentService;
import com.kgkt.tust.member.service.StaffService;
import com.kgkt.tust.member.vo.ParentVo;
import com.kgkt.tust.member.vo.StaffVo;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgq
 * @description Parent 和 Staff两类用户的修改密码接口实现
 */
@Slf4j
@RestController
@RequestMapping("/register")
@CrossOrigin
public class MemberUpdateController {

  @Resource private ParentService parentService;
  @Resource private StaffService staffService;

  /**
   * @param parent 创建的含有家长账号和密码两个属性的实体类
   * @return com.kgkt.tust.common.utils.R
   * @description 孩子家长修改密码
   */
  @PostMapping("/alter")
  @Transactional
  public R alterParent(ParentVo parent) {
    return parentService.alterParent(parent);
  }

  /**
   * @param staff 创建的含有员工账号和密码两个属性的实体类
   * @return com.kgkt.tust.common.utils.R
   * @description 员工修改密码
   */
  @PostMapping("/update")
  @Transactional
  public R alterStaff(StaffVo staff) {
    return staffService.alterStaff(staff);
  }
}
