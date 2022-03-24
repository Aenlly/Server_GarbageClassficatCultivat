package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.PaperTables;
import com.aenlly.rcc.entity.QuestionnaireTopics;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.TokenUtil;
import com.aenlly.rcc.utils.service.IAnswerQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "用户服务-知识测验管理控制器")
@RequestMapping("/answer-question")
public class AnswerQuestionController {

  @Resource private IAnswerQuestionService answerQuestionService;

  /**
   * 200：成功请求
   *
   * <p>300：代表已已答题
   *
   * <p>444：代表异常错误
   */
  @ApiOperation(value = "请求随机组卷批次号")
  @GetMapping("/getRandomBatchIndex")
  public CommonResult<String> getRandomBatchIndex(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("问卷名称") @RequestParam("naireName") String naireName) {
    try {
      String userId = TokenUtil.toUserId(token);
      return answerQuestionService.getRandomBatchIndex(userId, naireName);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求题目列表")
  @GetMapping("/getTopics")
  public CommonResult<List<QuestionnaireTopics>> getTopics(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("随机组卷批次号") @RequestParam("randomIndex") String randomIndex) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<QuestionnaireTopics> list = answerQuestionService.getTopics(userId, randomIndex);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "判断是否正确答案请求")
  @PostMapping("/isCorrect")
  public CommonResult<Long> isCorrect(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("题目编号") @RequestParam("topicId") Long topicId,
      @ApiParam("选项编号") @RequestParam("optionId") Long optionId,
      @ApiParam("随机组卷批次号") @RequestParam("randomIndex") String randomIndex) {
    try {
      String userId = TokenUtil.toUserId(token);
      optionId = answerQuestionService.isCorrect(userId, topicId, optionId, randomIndex);
      return resultOk(optionId);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "交卷请求")
  @PostMapping("/submitPaper")
  public CommonResult<Boolean> submitPaper(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("随机组卷批次号") @RequestParam("randomIndex") String randomIndex) {
    try {
      String userId = TokenUtil.toUserId(token);
      Boolean submitPaper = answerQuestionService.submitPaper(userId, randomIndex);
      return resultOk(submitPaper);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求答题记录")
  @GetMapping("/getPaperTablesLog")
  public CommonResult<List<PaperTables>> getPaperTablesLog(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("答题类型") @RequestParam("questionnaireName") String questionnaireName) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<PaperTables> list = answerQuestionService.getPaperTablesLog(userId, questionnaireName);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
