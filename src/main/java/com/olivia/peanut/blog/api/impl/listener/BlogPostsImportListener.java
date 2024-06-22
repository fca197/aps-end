package com.olivia.peanut.blog.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.blog.api.entity.blogPosts.BlogPostsImportReq;
import java.util.Map;

/**
 * 帖子清单(BlogPosts)文件导入监听
 *
 * @author peanut
 * @since 2024-06-21 13:23:45
 */
public class BlogPostsImportListener extends AnalysisEventListener<BlogPostsImportReq> {

  @Override
  public void invoke(BlogPostsImportReq data, AnalysisContext analysisContext) {
    //  文件校验
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 数据处理完毕后的操作（如果需要）
  }

  @Override
  public void onException(Exception exception, AnalysisContext context) throws Exception {
    // 异常处理
    super.onException(exception, context);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    //  log.info("headMap:{}", JSON.toJSONString(headMap));
    super.invokeHeadMap(headMap, context);
  }


}
