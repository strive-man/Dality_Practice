package com.kgkt.tust.member.controller;

import com.kgkt.tust.common.utils.PageUtils;
import com.kgkt.tust.common.utils.R;
import com.kgkt.tust.member.entity.StaffEntity;
import com.kgkt.tust.member.service.StaffService;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgq
 * @description StaffEntity类的CRUD方法，暂且保留后续功能可能用到，不过多注释
 */
@RestController
@RequestMapping("member/staff")
@CrossOrigin
public class StaffController {

  @Resource private StaffService staffService;

  /** 列表 */
  @GetMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = staffService.queryPage(params);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @GetMapping("/info/{staffId}")
  public R info(@PathVariable("staffId") String staffId) {
    StaffEntity staff = staffService.getById(staffId);

    return R.ok().put("staff", staff);
  }

  /** 保存 */
  @PostMapping("/save")
  public R save(@RequestBody StaffEntity staff) {
    staffService.save(staff);

    return R.ok();
  }

  /** 修改 */
  @PutMapping("/update")
  public R update(@RequestBody StaffEntity staff) {
    staffService.updateById(staff);

    return R.ok();
  }

  /** 删除 */
  @DeleteMapping("/delete")
  public R delete(@RequestBody String[] staffIds) {
    staffService.removeByIds(Arrays.asList(staffIds));

    return R.ok();
  }
}
