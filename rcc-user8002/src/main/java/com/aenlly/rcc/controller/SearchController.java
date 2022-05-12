package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.TokenUtil;
import com.aenlly.rcc.utils.service.ISearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 前端控制器

 @author aenlly
 @since 2021-12-14 */
@RestController
@Api(tags = "用户首页-垃圾搜索管理控制器")
@RequestMapping("/search")
public class SearchController {

    @Resource
    private ISearchService searchService;

    @ApiOperation(value = "文本搜索垃圾所属分类请求")
    @GetMapping("/getSearchText")
    public CommonResult<Collection<SearchLibrary>> getSearchText(
            @ApiParam("token") @RequestHeader("token") String token,
            @ApiParam("垃圾名称") @RequestParam("name") String name,
            @ApiParam("搜索类型") @RequestParam("type") SearchTypeEnum typeEnum) {
        try {
            String userId = TokenUtil.toUserId(token);
            Collection<SearchLibrary> list = searchService.searchText(name, userId, typeEnum);
            return resultOk(list);
        } catch (Exception e) {
            return resultError();
        }
    }

    @ApiOperation(value = "首页-语音搜索识别请求")
    @PostMapping("/searchVoice")
    public CommonResult<String> searchVoice(@ApiParam("语音文件") @RequestParam("voice") MultipartFile voice) {
        try {
            String result = searchService.searchVoice(voice);
            return resultOk(result);
        } catch (Exception e) {
            e.printStackTrace();
            return resultError();
        }
    }

    @ApiOperation(value = "首页-图片搜索识别请求")
    @PostMapping("/searchPicture")
    public CommonResult<String> searchPicture(@ApiParam("图片文件") @RequestParam("picture") MultipartFile picture) {
        try {
            String result = searchService.searchPicture(picture);
            return resultOk(result);
        } catch (Exception e) {
            return resultError();
        }
    }

    @ApiOperation(value = "用户所有搜索记录查询")
    @GetMapping("/get")
    public CommonResult<List<SearchUser>> getSearchList(@ApiParam("token") @RequestHeader("token") String token) {
        try {
            String userId = TokenUtil.toUserId(token);
            List<SearchUser> list = searchService.getSearchList(userId);
            return resultOk(list);
        } catch (Exception e) {
            e.printStackTrace();
            return resultError();
        }
    }

    @ApiOperation(value = "用户搜索记录条件查询")
    @GetMapping("/getSearchByName")
    public CommonResult<List<SearchUser>> getSearchByName(
            @ApiParam("token") @RequestHeader("token") String token,
            @ApiParam("搜索记录中的垃圾名称") @RequestParam("name") String name) {
        try {
            String userId = TokenUtil.toUserId(token);
            List<SearchUser> list = searchService.getSearchByName(userId, name);
            return resultOk(list);
        } catch (Exception e) {
            e.printStackTrace();
            return resultError();
        }
    }
}
