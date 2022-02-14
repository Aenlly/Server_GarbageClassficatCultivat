package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IQuestionBankService;
import com.aenlly.rcc.entity.ItemPoolSubjectView;
import com.aenlly.rcc.entity.ItemPoolTable;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 题库管理 前端控制器
 *
 * @author aenlly
 * @since 2022-02-04
 */
@RestController
@Api(tags = "题库信息管理控制器")
@RequestMapping("/question-bank")
public class QuestionBankController {

  @Resource private IQuestionBankService service;

  @ApiOperation(value = "请求题库与题目统计视图信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<ItemPoolSubjectView>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size) {
    try {
      IPage<ItemPoolSubjectView> list = service.getList(new Page<>(current, size));
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新题库信息请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("题库信息") ItemPoolTable entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
