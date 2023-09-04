package com.kgkt.tust.member.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgkt.tust.common.utils.PageUtils;
import com.kgkt.tust.common.utils.R;
import com.kgkt.tust.member.entity.ParentEntity;
import com.kgkt.tust.member.service.ParentService;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zgq
 * @description ParentEntity类的CRUD方法，暂且保留后续功能可能用到，不过多注释
 */
@Slf4j
@Controller
@RequestMapping("member/parent")
@CrossOrigin
public class ParentController {

  @Resource private ParentService parentService;
  @Resource private BaseMapper<ParentEntity> baseMapper;

  /** 列表 */
  @GetMapping("/list")
  @ResponseBody
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = parentService.queryPage(params);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @GetMapping("/info/{parentId}")
  public R info(@PathVariable("parentId") String parentId) {
    ParentEntity parent = parentService.getById(parentId);

    return R.ok().put("parent", parent);
  }

  /** 保存 */
  @PostMapping("/save")
  @ResponseBody
  public R save(@RequestBody ParentEntity parent) {
    parentService.save(parent);

    return R.ok();
  }

  /** 修改 */
  @PutMapping("/update")
  public R update(@RequestBody ParentEntity parent) {
    parentService.updateById(parent);

    return R.ok();
  }

  /** 删除 */
  @DeleteMapping("/delete")
  public R delete(@RequestBody String[] parentIds) {
    parentService.removeByIds(Arrays.asList(parentIds));
    return R.ok();
  }
}
