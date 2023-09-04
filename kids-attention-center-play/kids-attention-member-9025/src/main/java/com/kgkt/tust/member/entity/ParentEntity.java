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
@TableName("member_parent")
public class ParentEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 主键ID,雪花算法生成 */
  @TableId(type = IdType.ASSIGN_ID)
  private String parentId;
  /** 对应孩子的ID,不能为空，调用注册功能代码时会生成，此处不加 @NotBlank */
  private String parentKidId;
  /** 家长姓名 */
  @NotBlank(message = "家长姓名不能为空")
  private String parentName;
  /** 家长与学员的关系 */
  @NotBlank(message = "家长与学员关系不能为空")
  private String parentRelationship;
  /** 家长登录登录账户：手机号 */
  @NotBlank(message = "家长账号不能为空")
  private String parentAccount;
  /** 家长登录密码：RSA加密 */
  @NotBlank(message = "家长密码不能为空")
  @Pattern(
      regexp =
          "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,}$",
      message = "密码最少8位包含大、小写字母，数字，特殊字符中三种")
  private String parentPassword;
  /** 家长年龄 */
  private Integer parentAge;
  /** 手机号码 */
  @NotBlank(message = "家长手机号不能为空")
  private String parentPhone;
  /** 邮箱地址 */
  private String parentEmail;
  /** QQ账号 */
  private String parentQqAccount;
  /** 微信账号 */
  private String parentWeixinAccount;
  /** 乐观锁 */
  @Version private Integer version;
  /** 入库时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime addTime;
  /** 修改时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  /** 逻辑删除状态 */
  @TableLogic private Integer deleted;
}
