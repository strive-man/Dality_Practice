package com.kgkt.tust.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgkt.tust.common.exception.CodeEnum;
import com.kgkt.tust.common.exception.CustomException;
import com.kgkt.tust.common.utils.PageUtils;
import com.kgkt.tust.common.utils.Query;
import com.kgkt.tust.common.utils.R;
import com.kgkt.tust.member.dao.StaffDao;
import com.kgkt.tust.member.entity.StaffEntity;
import com.kgkt.tust.member.service.StaffService;
import com.kgkt.tust.member.util.CookieUtil;
import com.kgkt.tust.member.util.FinalFileUtil;
import com.kgkt.tust.member.util.PwdValidator;
import com.kgkt.tust.member.util.RsaUtil;
import com.kgkt.tust.member.util.UUIDUtil;
import com.kgkt.tust.member.vo.StaffVo;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author zgq
 * @description Service层的抽象方法实现类，实现功能和参数介绍可参看Service层
 */
@Slf4j
@Service("staffService")
public class StaffServiceImpl extends ServiceImpl<StaffDao, StaffEntity> implements StaffService {

  @Resource private StaffDao staffDao;
  @Resource private StaffService staffService;

  /**
   * @param params 前端需要条件查询时传来的参数
   * @return com.kgkt.tust.common.utils.PageUtils
   * @description 实现分页查询功能抽象方法，调用用common包封装的方法
   */
  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<StaffEntity> page =
        this.page(
            // Query是common包的封装好的方法充当工具类使用
            new Query<StaffEntity>().getPage(params), new QueryWrapper<StaffEntity>());
    return new PageUtils(page);
  }

  /**
   * @param staffVo 只有parentEntity实体类两个字段的构造类, request, response
   * @return java.lang.String
   * @description 完成员工登录功能的抽象方法
   */
  @Override
  public R doLogins(StaffVo staffVo, HttpServletRequest request, HttpServletResponse response) {
    // 从前端接收到输入账号和密码
    // 字段属性格式和空值判断由SR303校验相应注解完成【@Validated】
    String account = staffVo.getAccount();
    String password = staffVo.getPassword();
    // 根据account从数据库查询到相应用户
    StaffEntity staffEntity = staffDao.selectByStaffAccount(account);
    // 数据库用户不存在就抛出自定义异常
    if (null == staffEntity) {
      throw new CustomException(CodeEnum.ACCOUNT_NOT_EXIST);
    }
    // 拿到前端RSA加密后的密码，这里的加密先由后端工具类完成
    String front = RsaUtil.encryptByPublicKey(FinalFileUtil.PUB_KEY, password);
    // 解密前端发来的RSA加密密码
    String pwd = RsaUtil.decryptByPrivateKey(FinalFileUtil.SER_KEY, front);
    // 解密数据库得到的密码
    String passwords =
        RsaUtil.decryptByPrivateKey(FinalFileUtil.SER_KEY, staffEntity.getStaffPassword());
    // 前端输入密码为“123456”则是初始密码需要强制修改，给出提示信息
    if ("123456".equals(pwd) && "123456".equals(passwords)) {
      return R.error(CodeEnum.PASSWORD_ALTER.getCode(), CodeEnum.PASSWORD_ALTER.getMsg());
    }
    // 用户密码和数据库比对不一样，无论是不是初始密码都抛出异常信息
    if (!pwd.equals(
        RsaUtil.decryptByPrivateKey(FinalFileUtil.SER_KEY, staffEntity.getStaffPassword()))) {
      throw new CustomException(CodeEnum.LOGIN_ERROR);
    }
    // 上述条件通过，可以成功登录
    // 为用户生成唯一性的access,保存到session
    String access = UUIDUtil.uuid();
    // 设置session值
    request.getSession().setAttribute(access, staffEntity);
    // 保存access到前端cookie存储处
    CookieUtil.setCookie(response,"identify",access);
    log.info("走拦截器且已登录成功跳转后续页面");
    return R.ok("员工账号登录成功，前端已生成cookie信息");
  }

  /**
   * @author zgq
   * @param staff 只有parentEntity实体类两个字段【account，password】的构造类
   * @return com.kgkt.tust.common.utils.R
   * @description 完成员工修改密码功能的抽象方法
   */
  @Override
  public R alterStaff(@Validated StaffVo staff) {
    // 校验类注解验证前端输入新密码的格式是否合法
    if (staff != null) {
      // 工具类校验密码格式是否正确
      if (Boolean.TRUE.equals(PwdValidator.exam(staff.getPassword()))) {
        // 根据account锁定数据库用户
        StaffEntity staffEntity = staffDao.selectByStaffAccount(staff.getAccount());
        // 确定数据库存在此账户
        if (staffEntity != null) {
          // 这里等价于前端发的加密后的密码
          String front = RsaUtil.encryptByPublicKey(FinalFileUtil.PUB_KEY, staff.getPassword());
          // 比较新密码和旧密码是否重复
          if (!RsaUtil.decryptByPrivateKey(FinalFileUtil.SER_KEY, front)
              .equals(
                  RsaUtil.decryptByPrivateKey(
                      FinalFileUtil.SER_KEY, (staffEntity.getStaffPassword())))) {
            // 密码属性赋值为新密码
            staffEntity.setStaffPassword(front);
            // 进行数据库数据更新
            staffService.updateById(staffEntity);
            return R.ok("密码修改成功,跳转回登录页面").put("parent_data", front);
          }
          // 新旧密码重复
          return R.error(CodeEnum.PASSWORD_QUESTION.getCode(), CodeEnum.PASSWORD_QUESTION.getMsg());
        }
        // 账户不存在
        return R.error(CodeEnum.ACCOUNT_NOT_EXIST.getCode(), CodeEnum.ACCOUNT_NOT_EXIST.getMsg());
      }
      // 新密码的格式不合法返回错误信息提示
      return R.error(CodeEnum.PWD_ERROR.getCode(), CodeEnum.PWD_ERROR.getMsg());
    }
    // 未从前端接收到参数
    else {
      return R.error(CodeEnum.PARAMETER_LOSE.getCode(), CodeEnum.PARAMETER_LOSE.getMsg());
    }
  }
}
