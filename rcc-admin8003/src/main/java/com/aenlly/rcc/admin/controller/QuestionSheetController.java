package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IQuestionSheetService;
import com.aenlly.rcc.entity.AnswerSheet;
import com.aenlly.rcc.entity.PaperTables;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 答卷表 前端控制器
 *
 * @author aenlly
 * @since 2022-02-13
 */
@RestController
@Api(tags = "答卷信息管理控制器")
@RequestMapping("/questionSheet")
public class QuestionSheetController {

  @Resource private IQuestionSheetService service;

  @ApiOperation(value = "请求答卷信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<PaperTables>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("问卷id") @RequestParam("belongId") Long belongId,
      @Param("开始时间(非必须)") @RequestParam(value = "startTime", required = false) String startTime,
      @Param("结束时间(非必须)") @RequestParam(value = "endTime", required = false) String endTime) {
    try {
      IPage<PaperTables> list =
          service.getList(new Page<>(current, size), belongId, startTime, endTime);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据答卷id获取答案信息请求", httpMethod = "GET")
  @GetMapping("/getListByBelongPaperId/{belongPaperId}")
  public CommonResult<List<AnswerSheet>> getListByBelongPaperId(
      @Param("所属答卷id") @PathVariable("belongPaperId") Long belongPaperId) {
    try {
      List<AnswerSheet> list = service.getListByBelongPaperId(belongPaperId);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
