package com.kgkt.tust.member.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author zgq
 * @description 实体类 ParentEntity，校验注解不用审核，此模块功能用的是新建VO类
 */
@Data
@TableName("member_staff")
public class StaffEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 主键ID,雪花算法生成 */
  @TableId(type = IdType.ASSIGN_ID)
  private String staffId;
  /** 员工姓名 */
  @NotBlank(message = "员工姓名不能为空")
  private String staffName;
  /** 员工所属校区 */
  @NotBlank(message = "员工所属校区不能为空")
  private String staffSchool;
  /** 员工所属部门 */
  @NotBlank(message = "员工所属部门不能为空")
  private String staffDepartment;
  /** 员工角色 */
  @NotBlank(message = "员工角色不能为空")
  private String staffRole;
  /** 员工登录账户 */
  @NotBlank(message = "员工账号不能为空")
  private String staffAccount;
  /** 用户密码，RSA加密 */
  @NotBlank(message = "密码不能为空")
  @Pattern(
      regexp =
          "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,}$",
      message = "密码最少8位包含大、小写字母，数字，特殊字符中三种")
  private String staffPassword;
  /** 乐观锁 */
  @Version private Integer version;
  /** 写入时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime addTime;
  /** 修改时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  /** 逻辑删除状态 */
  @TableLogic private Integer deleted;
}
