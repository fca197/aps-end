package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecast.*;
import com.olivia.peanut.aps.model.ApsGoodsForecast;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * (ApsGoodsForecast)表服务接口
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
public interface ApsGoodsForecastService extends MPJBaseService<ApsGoodsForecast> {

  ApsGoodsForecastQueryListRes queryList(ApsGoodsForecastQueryListReq req);

  DynamicsPage<ApsGoodsForecastExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastDto> apsGoodsForecastDtoList);

  void downloadTemplate(Long id);

  UploadTemplateRes uploadTemplate(Long id, MultipartFile multipartFile);

  DynamicsPage<GetForecastDataByIdRes> getForecastDataById(GetForecastDataByIdReq req);

  ComputeRes compute(ComputeReq req);

  DynamicsPage<ComputeResultRes> computeResult(ComputeResultReq req);

  DeployRes deploy(DeployReq req);
}

