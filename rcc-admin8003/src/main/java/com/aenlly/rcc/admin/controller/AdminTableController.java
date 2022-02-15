package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IAdminTableService;
import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 管理员表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-08
 */
@RestController
@Api(tags = "管理员控制器")
@RequestMapping("/admin-table")
public class AdminTableController {

  @Resource private IAdminTableService service;

  @ApiOperation(value = "修改密码请求", httpMethod = "PUT")
  @PutMapping("/changePwd")
  public CommonResult<Boolean> changePwd(@Param("管理员接收实体") AdminTable entity) {
    try {
      Boolean b = service.changePwd(entity);
      return resultOk(b);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "修改密码请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Integer> update(@Param("管理员实体") AdminTable entity) {
    try {
      Integer b = service.update(entity);
      return resultOk(b);
    } catch (Exception e) {
      return resultError();
    }
  }
}
