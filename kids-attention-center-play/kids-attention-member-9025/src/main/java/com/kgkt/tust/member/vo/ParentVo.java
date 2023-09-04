package com.kgkt.tust.member.vo;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author zgq
 * @description 接收用户登录/修改密码时，前端发送的参数（account账号,password密码),因用到的参数少所以构造了VO实体类
 */
@Data
public class ParentVo {
  // 手机号码格式不对和用户账号不存在有一个就行了，后端不再做手机号格式验证
  @NotBlank private String account;
  @NotBlank private String password;
}
