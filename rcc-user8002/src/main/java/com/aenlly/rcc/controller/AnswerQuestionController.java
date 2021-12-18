package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.PaperTables;
import com.aenlly.rcc.entity.QuestionnaireTopics;
import com.aenlly.rcc.utils.AnswerQuestionService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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
      @Param("随机组卷批次号") @RequestParam("randomIndex") String randomIndex) {
    try {
      List<QuestionnaireTopics> list = answerQuestionService.getTopics(userId, randomIndex);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "判断是否正确答案请求", httpMethod = "POST")
  @PostMapping("/isCorrect")
  public CommonResult<Long> isCorrect(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("题目编号") @RequestParam("topicId") Long topicId,
      @Param("选项编号") @RequestParam("optionId") Long optionId,
      @Param("随机组卷批次号") @RequestParam("randomIndex") String randomIndex) {
    try {
      optionId = answerQuestionService.isCorrect(userId, topicId, optionId, randomIndex);
      return resultOk(optionId);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "交卷请求", httpMethod = "POST")
  @PostMapping("/submitPaper")
  public CommonResult<Boolean> submitPaper(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("随机组卷批次号") @RequestParam("randomIndex") String randomIndex) {
    try {
      Boolean submitPaper = answerQuestionService.submitPaper(userId, randomIndex);
      return resultOk(submitPaper);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求答题记录", httpMethod = "GET")
  @GetMapping("/getPaperTablesLog")
  public CommonResult<List<PaperTables>> getPaperTablesLog(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("答题类型") @RequestParam("questionnaireName") String questionnaireName) {
    try {
      List<PaperTables> list = answerQuestionService.getPaperTablesLog(userId, questionnaireName);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
