package com.olivia.peanut.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.model.ApsWorkshopSection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 工段信息(WorkshopSection)表数据库访问层
 *
 * @author peanut
 * @since 2023-12-28 17:29:06
 */
public interface WorkshopSectionDao extends BaseMapper<ApsWorkshopSection> {

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<WorkshopSection> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<ApsWorkshopSection> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<WorkshopSection> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<ApsWorkshopSection> entities);

}

