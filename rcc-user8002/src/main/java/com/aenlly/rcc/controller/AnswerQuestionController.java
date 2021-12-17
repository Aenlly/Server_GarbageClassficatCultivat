package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.QuestionnaireTopics;
import com.aenlly.rcc.utils.AnswerQuestionService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 知识测验管理控制器
 *
 * @author Aenlly
 * @create by date 2021/12/17 17:06
 * @projectName RefuseClassificationCultivate
 */
@RestController
@ApiOperation("知识测验-管理控制器")
@RequestMapping("/answer-question")
public class AnswerQuestionController {

  @Resource AnswerQuestionService answerQuestionService;

  /**
   * 200：成功请求
   *
   * <p>300：代表已已答题
   *
   * <p>444：代表异常错误
   */
  @ApiOperation(value = "请求随机组卷批次号", httpMethod = "GET")
  @GetMapping("/getRandomBatchIndex")
  public CommonResult<String> getRandomBatchIndex(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("问卷名称") @RequestParam("naireName") String naireName) {
    try {
      return answerQuestionService.getRandomBatchIndex(userId, naireName);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求题目列表", httpMethod = "GET")
  @GetMapping("/getTopics")
  public CommonResult<List<QuestionnaireTopics>> getTopics(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("问卷名称") @RequestParam("randomIndex") String randomIndex) {
    try {
      List<QuestionnaireTopics> list = answerQuestionService.getTopics(userId, randomIndex);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
