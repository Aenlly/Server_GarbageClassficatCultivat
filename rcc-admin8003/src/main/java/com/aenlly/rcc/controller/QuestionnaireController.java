package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Questionnaire;
import com.aenlly.rcc.service.IQuestionnaireService;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 问卷表 前端控制器
 *
 * @author aenlly
 * @since 2022-02-13
 */
@RestController
@Api(tags = "问卷信息管理控制器")
@RequestMapping("/questionnaire")
public class QuestionnaireController {

  @Resource private IQuestionnaireService service;

  @ApiOperation(value = "请求问卷信息数据")
  @GetMapping("/getList")
  public CommonResult<IPage<Questionnaire>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size) {
    try {
      IPage<Questionnaire> list = service.getList(new Page<>(current, size));
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
