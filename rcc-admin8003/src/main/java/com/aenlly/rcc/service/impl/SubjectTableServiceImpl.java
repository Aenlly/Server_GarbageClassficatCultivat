package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.ISubjectTableService;
import com.aenlly.rcc.utils.ExcelFileUtil;
import com.aenlly.rcc.entity.OptionsTable;
import com.aenlly.rcc.entity.SubjectTable;
import com.aenlly.rcc.enums.CorrectlyOrNotEnum;
import com.aenlly.rcc.execl.QuizSubjectExecl;
import com.aenlly.rcc.mapper.SubjectTableMapper;
import com.aenlly.rcc.service.IOptionsTableService;
import com.aenlly.rcc.utils.wrapper.SubjectTableWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目表 服务实现类
 *
 * @author aenlly
 * @since 2022-02-11
 */
@Service
@Slf4j
public class SubjectTableServiceImpl extends ServiceImpl<SubjectTableMapper, SubjectTable>
    implements ISubjectTableService {
  /** 选项表服务对象 */
  @Resource private IOptionsTableService service;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param belongId 所属题库id
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<SubjectTable> getList(Page<SubjectTable> page, Long belongId, String text) {
    Wrapper<SubjectTable> wrapper = SubjectTableWrapperUtil.queryListPage(belongId, text);
    return this.page(page, wrapper);
  }

  /**
   * 导出题目导入模板给用户
   *
   * @param response 用于传输
   * @throws IOException 文件创建异常
   */
  @Override
  public void getExcelFile(HttpServletResponse response) throws IOException {
    ExcelFileUtil.exportExcel(response, QuizSubjectExecl.class);
  }

  /**
   * 上传文件进行解析并填充到数据库中
   *
   * @param file 文件
   * @param belongId 所属题库
   * @return 是否成功
   */
  @Override
  public Boolean uploadExcelFile(MultipartFile file, Long belongId) {
    List<QuizSubjectExecl> list = ExcelFileUtil.importExcelFile(file, QuizSubjectExecl.class);
    // 遍历导入数据库中
    list.forEach(
        l -> {
          // 导入题目
          SubjectTable subjectTable = new SubjectTable();
          subjectTable.setAnalysis(l.getAnalysis());
          subjectTable.setTopicName(l.getSubject());
          subjectTable.setScore(l.getScore());
          subjectTable.setDatabankId(Math.toIntExact(belongId));
          boolean save = this.save(subjectTable);
          // 判断题目导入是否成功
          if (save) {
            // 导入选项
            List<OptionsTable> optionsTableList = new ArrayList<>();
            optionsTableList.add(
                new OptionsTable(
                    l.getOptionsA(),
                    subjectTable.getId(),
                    l.getCorrectlyOptions() == 1
                        ? CorrectlyOrNotEnum.CPRRECTLY
                        : CorrectlyOrNotEnum.NOT));
            optionsTableList.add(
                new OptionsTable(
                    l.getOptionsB(),
                    subjectTable.getId(),
                    l.getCorrectlyOptions() == 2
                        ? CorrectlyOrNotEnum.CPRRECTLY
                        : CorrectlyOrNotEnum.NOT));
            optionsTableList.add(
                new OptionsTable(
                    l.getOptionsC(),
                    subjectTable.getId(),
                    l.getCorrectlyOptions() == 3
                        ? CorrectlyOrNotEnum.CPRRECTLY
                        : CorrectlyOrNotEnum.NOT));
            optionsTableList.add(
                new OptionsTable(
                    l.getOptionsD(),
                    subjectTable.getId(),
                    l.getCorrectlyOptions() == 4
                        ? CorrectlyOrNotEnum.CPRRECTLY
                        : CorrectlyOrNotEnum.NOT));
            boolean saveBatch = service.saveBatch(optionsTableList);
            if (!saveBatch) {
              throw new NullPointerException();
            }
          }
        });
    return true;
  }
}
