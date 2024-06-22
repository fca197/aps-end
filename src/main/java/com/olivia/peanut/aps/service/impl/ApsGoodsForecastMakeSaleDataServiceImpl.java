package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMakeSaleDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeSaleDataService;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (ApsGoodsForecastMakeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
@Service("apsGoodsForecastMakeSaleDataService")
@Transactional
public class ApsGoodsForecastMakeSaleDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMakeSaleDataMapper, ApsGoodsForecastMakeSaleData> implements
    ApsGoodsForecastMakeSaleDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMakeSaleDataQueryListRes queryList(ApsGoodsForecastMakeSaleDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeSaleData> list = this.list(q);

    List<ApsGoodsForecastMakeSaleDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMakeSaleDataDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new ApsGoodsForecastMakeSaleDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeSaleDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMakeSaleData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMakeSaleData> list = this.page(page, q);
      IPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsGoodsForecastMakeSaleDataDto> apsGoodsForecastMakeSaleDataDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMakeSaleDataDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> getWrapper(ApsGoodsForecastMakeSaleDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMakeSaleData::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMakeMonthId()), ApsGoodsForecastMakeSaleData::getMakeMonthId, obj.getMakeMonthId())
          .eq(StringUtils.isNoneBlank(obj.getSaleConfigCode()), ApsGoodsForecastMakeSaleData::getSaleConfigCode, obj.getSaleConfigCode())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMakeSaleData::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getDayNum1()), ApsGoodsForecastMakeSaleData::getDayNum1, obj.getDayNum1())
          .eq(Objects.nonNull(obj.getDayNum2()), ApsGoodsForecastMakeSaleData::getDayNum2, obj.getDayNum2())
          .eq(Objects.nonNull(obj.getDayNum3()), ApsGoodsForecastMakeSaleData::getDayNum3, obj.getDayNum3())
          .eq(Objects.nonNull(obj.getDayNum4()), ApsGoodsForecastMakeSaleData::getDayNum4, obj.getDayNum4())
          .eq(Objects.nonNull(obj.getDayNum5()), ApsGoodsForecastMakeSaleData::getDayNum5, obj.getDayNum5())
          .eq(Objects.nonNull(obj.getDayNum6()), ApsGoodsForecastMakeSaleData::getDayNum6, obj.getDayNum6())
          .eq(Objects.nonNull(obj.getDayNum7()), ApsGoodsForecastMakeSaleData::getDayNum7, obj.getDayNum7())
          .eq(Objects.nonNull(obj.getDayNum8()), ApsGoodsForecastMakeSaleData::getDayNum8, obj.getDayNum8())
          .eq(Objects.nonNull(obj.getDayNum9()), ApsGoodsForecastMakeSaleData::getDayNum9, obj.getDayNum9())
          .eq(Objects.nonNull(obj.getDayNum10()), ApsGoodsForecastMakeSaleData::getDayNum10, obj.getDayNum10())
          .eq(Objects.nonNull(obj.getDayNum11()), ApsGoodsForecastMakeSaleData::getDayNum11, obj.getDayNum11())
          .eq(Objects.nonNull(obj.getDayNum12()), ApsGoodsForecastMakeSaleData::getDayNum12, obj.getDayNum12())
          .eq(Objects.nonNull(obj.getDayNum13()), ApsGoodsForecastMakeSaleData::getDayNum13, obj.getDayNum13())
          .eq(Objects.nonNull(obj.getDayNum14()), ApsGoodsForecastMakeSaleData::getDayNum14, obj.getDayNum14())
          .eq(Objects.nonNull(obj.getDayNum15()), ApsGoodsForecastMakeSaleData::getDayNum15, obj.getDayNum15())
          .eq(Objects.nonNull(obj.getDayNum16()), ApsGoodsForecastMakeSaleData::getDayNum16, obj.getDayNum16())
          .eq(Objects.nonNull(obj.getDayNum17()), ApsGoodsForecastMakeSaleData::getDayNum17, obj.getDayNum17())
          .eq(Objects.nonNull(obj.getDayNum18()), ApsGoodsForecastMakeSaleData::getDayNum18, obj.getDayNum18())
          .eq(Objects.nonNull(obj.getDayNum19()), ApsGoodsForecastMakeSaleData::getDayNum19, obj.getDayNum19())
          .eq(Objects.nonNull(obj.getDayNum20()), ApsGoodsForecastMakeSaleData::getDayNum20, obj.getDayNum20())
          .eq(Objects.nonNull(obj.getDayNum21()), ApsGoodsForecastMakeSaleData::getDayNum21, obj.getDayNum21())
          .eq(Objects.nonNull(obj.getDayNum22()), ApsGoodsForecastMakeSaleData::getDayNum22, obj.getDayNum22())
          .eq(Objects.nonNull(obj.getDayNum23()), ApsGoodsForecastMakeSaleData::getDayNum23, obj.getDayNum23())
          .eq(Objects.nonNull(obj.getDayNum24()), ApsGoodsForecastMakeSaleData::getDayNum24, obj.getDayNum24())
          .eq(Objects.nonNull(obj.getDayNum25()), ApsGoodsForecastMakeSaleData::getDayNum25, obj.getDayNum25())
          .eq(Objects.nonNull(obj.getDayNum26()), ApsGoodsForecastMakeSaleData::getDayNum26, obj.getDayNum26())
          .eq(Objects.nonNull(obj.getDayNum27()), ApsGoodsForecastMakeSaleData::getDayNum27, obj.getDayNum27())
          .eq(Objects.nonNull(obj.getDayNum28()), ApsGoodsForecastMakeSaleData::getDayNum28, obj.getDayNum28())
          .eq(Objects.nonNull(obj.getDayNum29()), ApsGoodsForecastMakeSaleData::getDayNum29, obj.getDayNum29())
          .eq(Objects.nonNull(obj.getDayNum30()), ApsGoodsForecastMakeSaleData::getDayNum30, obj.getDayNum30())
          .eq(Objects.nonNull(obj.getDayNum31()), ApsGoodsForecastMakeSaleData::getDayNum31, obj.getDayNum31())
          .eq(Objects.nonNull(obj.getDayNum32()), ApsGoodsForecastMakeSaleData::getDayNum32, obj.getDayNum32())
          .eq(Objects.nonNull(obj.getDayNum33()), ApsGoodsForecastMakeSaleData::getDayNum33, obj.getDayNum33())
          .eq(Objects.nonNull(obj.getDayNum34()), ApsGoodsForecastMakeSaleData::getDayNum34, obj.getDayNum34())
          .eq(Objects.nonNull(obj.getDayNum35()), ApsGoodsForecastMakeSaleData::getDayNum35, obj.getDayNum35())
          .eq(Objects.nonNull(obj.getDayNum36()), ApsGoodsForecastMakeSaleData::getDayNum36, obj.getDayNum36())
          .eq(Objects.nonNull(obj.getDayNum37()), ApsGoodsForecastMakeSaleData::getDayNum37, obj.getDayNum37())
          .eq(Objects.nonNull(obj.getDayNum38()), ApsGoodsForecastMakeSaleData::getDayNum38, obj.getDayNum38())
          .eq(Objects.nonNull(obj.getDayNum39()), ApsGoodsForecastMakeSaleData::getDayNum39, obj.getDayNum39())
          .eq(Objects.nonNull(obj.getDayNum40()), ApsGoodsForecastMakeSaleData::getDayNum40, obj.getDayNum40())
          .eq(Objects.nonNull(obj.getDayNum41()), ApsGoodsForecastMakeSaleData::getDayNum41, obj.getDayNum41())
          .eq(Objects.nonNull(obj.getDayNum42()), ApsGoodsForecastMakeSaleData::getDayNum42, obj.getDayNum42())
          .eq(Objects.nonNull(obj.getDayNum43()), ApsGoodsForecastMakeSaleData::getDayNum43, obj.getDayNum43())
          .eq(Objects.nonNull(obj.getDayNum44()), ApsGoodsForecastMakeSaleData::getDayNum44, obj.getDayNum44())
          .eq(Objects.nonNull(obj.getDayNum45()), ApsGoodsForecastMakeSaleData::getDayNum45, obj.getDayNum45())
          .eq(Objects.nonNull(obj.getDayNum46()), ApsGoodsForecastMakeSaleData::getDayNum46, obj.getDayNum46())
          .eq(Objects.nonNull(obj.getDayNum47()), ApsGoodsForecastMakeSaleData::getDayNum47, obj.getDayNum47())
          .eq(Objects.nonNull(obj.getDayNum48()), ApsGoodsForecastMakeSaleData::getDayNum48, obj.getDayNum48())
          .eq(Objects.nonNull(obj.getDayNum49()), ApsGoodsForecastMakeSaleData::getDayNum49, obj.getDayNum49())
          .eq(Objects.nonNull(obj.getDayNum50()), ApsGoodsForecastMakeSaleData::getDayNum50, obj.getDayNum50())
          .eq(Objects.nonNull(obj.getDayNum51()), ApsGoodsForecastMakeSaleData::getDayNum51, obj.getDayNum51())
          .eq(Objects.nonNull(obj.getDayNum52()), ApsGoodsForecastMakeSaleData::getDayNum52, obj.getDayNum52())
          .eq(Objects.nonNull(obj.getDayNum53()), ApsGoodsForecastMakeSaleData::getDayNum53, obj.getDayNum53())
          .eq(Objects.nonNull(obj.getDayNum54()), ApsGoodsForecastMakeSaleData::getDayNum54, obj.getDayNum54())
          .eq(Objects.nonNull(obj.getDayNum55()), ApsGoodsForecastMakeSaleData::getDayNum55, obj.getDayNum55())
          .eq(Objects.nonNull(obj.getDayNum56()), ApsGoodsForecastMakeSaleData::getDayNum56, obj.getDayNum56())
          .eq(Objects.nonNull(obj.getDayNum57()), ApsGoodsForecastMakeSaleData::getDayNum57, obj.getDayNum57())
          .eq(Objects.nonNull(obj.getDayNum58()), ApsGoodsForecastMakeSaleData::getDayNum58, obj.getDayNum58())
          .eq(Objects.nonNull(obj.getDayNum59()), ApsGoodsForecastMakeSaleData::getDayNum59, obj.getDayNum59())
          .eq(Objects.nonNull(obj.getDayNum60()), ApsGoodsForecastMakeSaleData::getDayNum60, obj.getDayNum60())
          .eq(Objects.nonNull(obj.getDayNum61()), ApsGoodsForecastMakeSaleData::getDayNum61, obj.getDayNum61())
          .eq(Objects.nonNull(obj.getDayNum62()), ApsGoodsForecastMakeSaleData::getDayNum62, obj.getDayNum62())
          .eq(Objects.nonNull(obj.getDayNum63()), ApsGoodsForecastMakeSaleData::getDayNum63, obj.getDayNum63())
          .eq(Objects.nonNull(obj.getDayNum64()), ApsGoodsForecastMakeSaleData::getDayNum64, obj.getDayNum64())
          .eq(Objects.nonNull(obj.getDayNum65()), ApsGoodsForecastMakeSaleData::getDayNum65, obj.getDayNum65())
          .eq(Objects.nonNull(obj.getDayNum66()), ApsGoodsForecastMakeSaleData::getDayNum66, obj.getDayNum66())
          .eq(Objects.nonNull(obj.getDayNum67()), ApsGoodsForecastMakeSaleData::getDayNum67, obj.getDayNum67())
          .eq(Objects.nonNull(obj.getDayNum68()), ApsGoodsForecastMakeSaleData::getDayNum68, obj.getDayNum68())
          .eq(Objects.nonNull(obj.getDayNum69()), ApsGoodsForecastMakeSaleData::getDayNum69, obj.getDayNum69())
          .eq(Objects.nonNull(obj.getDayNum70()), ApsGoodsForecastMakeSaleData::getDayNum70, obj.getDayNum70())
          .eq(Objects.nonNull(obj.getDayNum71()), ApsGoodsForecastMakeSaleData::getDayNum71, obj.getDayNum71())
          .eq(Objects.nonNull(obj.getDayNum72()), ApsGoodsForecastMakeSaleData::getDayNum72, obj.getDayNum72())
          .eq(Objects.nonNull(obj.getDayNum73()), ApsGoodsForecastMakeSaleData::getDayNum73, obj.getDayNum73())
          .eq(Objects.nonNull(obj.getDayNum74()), ApsGoodsForecastMakeSaleData::getDayNum74, obj.getDayNum74())
          .eq(Objects.nonNull(obj.getDayNum75()), ApsGoodsForecastMakeSaleData::getDayNum75, obj.getDayNum75())
          .eq(Objects.nonNull(obj.getDayNum76()), ApsGoodsForecastMakeSaleData::getDayNum76, obj.getDayNum76())
          .eq(Objects.nonNull(obj.getDayNum77()), ApsGoodsForecastMakeSaleData::getDayNum77, obj.getDayNum77())
          .eq(Objects.nonNull(obj.getDayNum78()), ApsGoodsForecastMakeSaleData::getDayNum78, obj.getDayNum78())
          .eq(Objects.nonNull(obj.getDayNum79()), ApsGoodsForecastMakeSaleData::getDayNum79, obj.getDayNum79())
          .eq(Objects.nonNull(obj.getDayNum80()), ApsGoodsForecastMakeSaleData::getDayNum80, obj.getDayNum80())
          .eq(Objects.nonNull(obj.getDayNum81()), ApsGoodsForecastMakeSaleData::getDayNum81, obj.getDayNum81())
          .eq(Objects.nonNull(obj.getDayNum82()), ApsGoodsForecastMakeSaleData::getDayNum82, obj.getDayNum82())
          .eq(Objects.nonNull(obj.getDayNum83()), ApsGoodsForecastMakeSaleData::getDayNum83, obj.getDayNum83())
          .eq(Objects.nonNull(obj.getDayNum84()), ApsGoodsForecastMakeSaleData::getDayNum84, obj.getDayNum84())
          .eq(Objects.nonNull(obj.getDayNum85()), ApsGoodsForecastMakeSaleData::getDayNum85, obj.getDayNum85())
          .eq(Objects.nonNull(obj.getDayNum86()), ApsGoodsForecastMakeSaleData::getDayNum86, obj.getDayNum86())
          .eq(Objects.nonNull(obj.getDayNum87()), ApsGoodsForecastMakeSaleData::getDayNum87, obj.getDayNum87())
          .eq(Objects.nonNull(obj.getDayNum88()), ApsGoodsForecastMakeSaleData::getDayNum88, obj.getDayNum88())
          .eq(Objects.nonNull(obj.getDayNum89()), ApsGoodsForecastMakeSaleData::getDayNum89, obj.getDayNum89())
          .eq(Objects.nonNull(obj.getDayNum90()), ApsGoodsForecastMakeSaleData::getDayNum90, obj.getDayNum90())
          .eq(Objects.nonNull(obj.getDayNum91()), ApsGoodsForecastMakeSaleData::getDayNum91, obj.getDayNum91())
          .eq(Objects.nonNull(obj.getDayNum92()), ApsGoodsForecastMakeSaleData::getDayNum92, obj.getDayNum92())
          .eq(Objects.nonNull(obj.getDayNum93()), ApsGoodsForecastMakeSaleData::getDayNum93, obj.getDayNum93())
          .eq(Objects.nonNull(obj.getDayNum94()), ApsGoodsForecastMakeSaleData::getDayNum94, obj.getDayNum94())
          .eq(Objects.nonNull(obj.getDayNum95()), ApsGoodsForecastMakeSaleData::getDayNum95, obj.getDayNum95())
          .eq(Objects.nonNull(obj.getDayNum96()), ApsGoodsForecastMakeSaleData::getDayNum96, obj.getDayNum96())
          .eq(Objects.nonNull(obj.getDayNum97()), ApsGoodsForecastMakeSaleData::getDayNum97, obj.getDayNum97())
          .eq(Objects.nonNull(obj.getDayNum98()), ApsGoodsForecastMakeSaleData::getDayNum98, obj.getDayNum98())
          .eq(Objects.nonNull(obj.getDayNum99()), ApsGoodsForecastMakeSaleData::getDayNum99, obj.getDayNum99())
          .eq(Objects.nonNull(obj.getDayNum100()), ApsGoodsForecastMakeSaleData::getDayNum100, obj.getDayNum100())
          .eq(Objects.nonNull(obj.getDayNum101()), ApsGoodsForecastMakeSaleData::getDayNum101, obj.getDayNum101())
          .eq(Objects.nonNull(obj.getDayNum102()), ApsGoodsForecastMakeSaleData::getDayNum102, obj.getDayNum102())
          .eq(Objects.nonNull(obj.getDayNum103()), ApsGoodsForecastMakeSaleData::getDayNum103, obj.getDayNum103())
          .eq(Objects.nonNull(obj.getDayNum104()), ApsGoodsForecastMakeSaleData::getDayNum104, obj.getDayNum104())
          .eq(Objects.nonNull(obj.getDayNum105()), ApsGoodsForecastMakeSaleData::getDayNum105, obj.getDayNum105())
          .eq(Objects.nonNull(obj.getDayNum106()), ApsGoodsForecastMakeSaleData::getDayNum106, obj.getDayNum106())
          .eq(Objects.nonNull(obj.getDayNum107()), ApsGoodsForecastMakeSaleData::getDayNum107, obj.getDayNum107())
          .eq(Objects.nonNull(obj.getDayNum108()), ApsGoodsForecastMakeSaleData::getDayNum108, obj.getDayNum108())
          .eq(Objects.nonNull(obj.getDayNum109()), ApsGoodsForecastMakeSaleData::getDayNum109, obj.getDayNum109())
          .eq(Objects.nonNull(obj.getDayNum110()), ApsGoodsForecastMakeSaleData::getDayNum110, obj.getDayNum110())
          .eq(Objects.nonNull(obj.getDayNum111()), ApsGoodsForecastMakeSaleData::getDayNum111, obj.getDayNum111())
          .eq(Objects.nonNull(obj.getDayNum112()), ApsGoodsForecastMakeSaleData::getDayNum112, obj.getDayNum112())
          .eq(Objects.nonNull(obj.getDayNum113()), ApsGoodsForecastMakeSaleData::getDayNum113, obj.getDayNum113())
          .eq(Objects.nonNull(obj.getDayNum114()), ApsGoodsForecastMakeSaleData::getDayNum114, obj.getDayNum114())
          .eq(Objects.nonNull(obj.getDayNum115()), ApsGoodsForecastMakeSaleData::getDayNum115, obj.getDayNum115())
          .eq(Objects.nonNull(obj.getDayNum116()), ApsGoodsForecastMakeSaleData::getDayNum116, obj.getDayNum116())
          .eq(Objects.nonNull(obj.getDayNum117()), ApsGoodsForecastMakeSaleData::getDayNum117, obj.getDayNum117())
          .eq(Objects.nonNull(obj.getDayNum118()), ApsGoodsForecastMakeSaleData::getDayNum118, obj.getDayNum118())
          .eq(Objects.nonNull(obj.getDayNum119()), ApsGoodsForecastMakeSaleData::getDayNum119, obj.getDayNum119())
          .eq(Objects.nonNull(obj.getDayNum120()), ApsGoodsForecastMakeSaleData::getDayNum120, obj.getDayNum120())
          .eq(Objects.nonNull(obj.getDayNum121()), ApsGoodsForecastMakeSaleData::getDayNum121, obj.getDayNum121())
          .eq(Objects.nonNull(obj.getDayNum122()), ApsGoodsForecastMakeSaleData::getDayNum122, obj.getDayNum122())
          .eq(Objects.nonNull(obj.getDayNum123()), ApsGoodsForecastMakeSaleData::getDayNum123, obj.getDayNum123())
          .eq(Objects.nonNull(obj.getDayNum124()), ApsGoodsForecastMakeSaleData::getDayNum124, obj.getDayNum124())
          .eq(Objects.nonNull(obj.getDayNum125()), ApsGoodsForecastMakeSaleData::getDayNum125, obj.getDayNum125())
          .eq(Objects.nonNull(obj.getDayNum126()), ApsGoodsForecastMakeSaleData::getDayNum126, obj.getDayNum126())
          .eq(Objects.nonNull(obj.getDayNum127()), ApsGoodsForecastMakeSaleData::getDayNum127, obj.getDayNum127())
          .eq(Objects.nonNull(obj.getDayNum128()), ApsGoodsForecastMakeSaleData::getDayNum128, obj.getDayNum128())
          .eq(Objects.nonNull(obj.getDayNum129()), ApsGoodsForecastMakeSaleData::getDayNum129, obj.getDayNum129())
          .eq(Objects.nonNull(obj.getDayNum130()), ApsGoodsForecastMakeSaleData::getDayNum130, obj.getDayNum130())
          .eq(Objects.nonNull(obj.getDayNum131()), ApsGoodsForecastMakeSaleData::getDayNum131, obj.getDayNum131())
          .eq(Objects.nonNull(obj.getDayNum132()), ApsGoodsForecastMakeSaleData::getDayNum132, obj.getDayNum132())
          .eq(Objects.nonNull(obj.getDayNum133()), ApsGoodsForecastMakeSaleData::getDayNum133, obj.getDayNum133())
          .eq(Objects.nonNull(obj.getDayNum134()), ApsGoodsForecastMakeSaleData::getDayNum134, obj.getDayNum134())
          .eq(Objects.nonNull(obj.getDayNum135()), ApsGoodsForecastMakeSaleData::getDayNum135, obj.getDayNum135())
          .eq(Objects.nonNull(obj.getDayNum136()), ApsGoodsForecastMakeSaleData::getDayNum136, obj.getDayNum136())
          .eq(Objects.nonNull(obj.getDayNum137()), ApsGoodsForecastMakeSaleData::getDayNum137, obj.getDayNum137())
          .eq(Objects.nonNull(obj.getDayNum138()), ApsGoodsForecastMakeSaleData::getDayNum138, obj.getDayNum138())
          .eq(Objects.nonNull(obj.getDayNum139()), ApsGoodsForecastMakeSaleData::getDayNum139, obj.getDayNum139())
          .eq(Objects.nonNull(obj.getDayNum140()), ApsGoodsForecastMakeSaleData::getDayNum140, obj.getDayNum140())
          .eq(Objects.nonNull(obj.getDayNum141()), ApsGoodsForecastMakeSaleData::getDayNum141, obj.getDayNum141())
          .eq(Objects.nonNull(obj.getDayNum142()), ApsGoodsForecastMakeSaleData::getDayNum142, obj.getDayNum142())
          .eq(Objects.nonNull(obj.getDayNum143()), ApsGoodsForecastMakeSaleData::getDayNum143, obj.getDayNum143())
          .eq(Objects.nonNull(obj.getDayNum144()), ApsGoodsForecastMakeSaleData::getDayNum144, obj.getDayNum144())
          .eq(Objects.nonNull(obj.getDayNum145()), ApsGoodsForecastMakeSaleData::getDayNum145, obj.getDayNum145())
          .eq(Objects.nonNull(obj.getDayNum146()), ApsGoodsForecastMakeSaleData::getDayNum146, obj.getDayNum146())
          .eq(Objects.nonNull(obj.getDayNum147()), ApsGoodsForecastMakeSaleData::getDayNum147, obj.getDayNum147())
          .eq(Objects.nonNull(obj.getDayNum148()), ApsGoodsForecastMakeSaleData::getDayNum148, obj.getDayNum148())
          .eq(Objects.nonNull(obj.getDayNum149()), ApsGoodsForecastMakeSaleData::getDayNum149, obj.getDayNum149())
          .eq(Objects.nonNull(obj.getDayNum150()), ApsGoodsForecastMakeSaleData::getDayNum150, obj.getDayNum150())
          .eq(Objects.nonNull(obj.getDayNum151()), ApsGoodsForecastMakeSaleData::getDayNum151, obj.getDayNum151())
          .eq(Objects.nonNull(obj.getDayNum152()), ApsGoodsForecastMakeSaleData::getDayNum152, obj.getDayNum152())
          .eq(Objects.nonNull(obj.getDayNum153()), ApsGoodsForecastMakeSaleData::getDayNum153, obj.getDayNum153())
          .eq(Objects.nonNull(obj.getDayNum154()), ApsGoodsForecastMakeSaleData::getDayNum154, obj.getDayNum154())
          .eq(Objects.nonNull(obj.getDayNum155()), ApsGoodsForecastMakeSaleData::getDayNum155, obj.getDayNum155())
          .eq(Objects.nonNull(obj.getDayNum156()), ApsGoodsForecastMakeSaleData::getDayNum156, obj.getDayNum156())
          .eq(Objects.nonNull(obj.getDayNum157()), ApsGoodsForecastMakeSaleData::getDayNum157, obj.getDayNum157())
          .eq(Objects.nonNull(obj.getDayNum158()), ApsGoodsForecastMakeSaleData::getDayNum158, obj.getDayNum158())
          .eq(Objects.nonNull(obj.getDayNum159()), ApsGoodsForecastMakeSaleData::getDayNum159, obj.getDayNum159())
          .eq(Objects.nonNull(obj.getDayNum160()), ApsGoodsForecastMakeSaleData::getDayNum160, obj.getDayNum160())
          .eq(Objects.nonNull(obj.getDayNum161()), ApsGoodsForecastMakeSaleData::getDayNum161, obj.getDayNum161())
          .eq(Objects.nonNull(obj.getDayNum162()), ApsGoodsForecastMakeSaleData::getDayNum162, obj.getDayNum162())
          .eq(Objects.nonNull(obj.getDayNum163()), ApsGoodsForecastMakeSaleData::getDayNum163, obj.getDayNum163())
          .eq(Objects.nonNull(obj.getDayNum164()), ApsGoodsForecastMakeSaleData::getDayNum164, obj.getDayNum164())
          .eq(Objects.nonNull(obj.getDayNum165()), ApsGoodsForecastMakeSaleData::getDayNum165, obj.getDayNum165())
          .eq(Objects.nonNull(obj.getDayNum166()), ApsGoodsForecastMakeSaleData::getDayNum166, obj.getDayNum166())
          .eq(Objects.nonNull(obj.getDayNum167()), ApsGoodsForecastMakeSaleData::getDayNum167, obj.getDayNum167())
          .eq(Objects.nonNull(obj.getDayNum168()), ApsGoodsForecastMakeSaleData::getDayNum168, obj.getDayNum168())
          .eq(Objects.nonNull(obj.getDayNum169()), ApsGoodsForecastMakeSaleData::getDayNum169, obj.getDayNum169())
          .eq(Objects.nonNull(obj.getDayNum170()), ApsGoodsForecastMakeSaleData::getDayNum170, obj.getDayNum170())
          .eq(Objects.nonNull(obj.getDayNum171()), ApsGoodsForecastMakeSaleData::getDayNum171, obj.getDayNum171())
          .eq(Objects.nonNull(obj.getDayNum172()), ApsGoodsForecastMakeSaleData::getDayNum172, obj.getDayNum172())
          .eq(Objects.nonNull(obj.getDayNum173()), ApsGoodsForecastMakeSaleData::getDayNum173, obj.getDayNum173())
          .eq(Objects.nonNull(obj.getDayNum174()), ApsGoodsForecastMakeSaleData::getDayNum174, obj.getDayNum174())
          .eq(Objects.nonNull(obj.getDayNum175()), ApsGoodsForecastMakeSaleData::getDayNum175, obj.getDayNum175())
          .eq(Objects.nonNull(obj.getDayNum176()), ApsGoodsForecastMakeSaleData::getDayNum176, obj.getDayNum176())
          .eq(Objects.nonNull(obj.getDayNum177()), ApsGoodsForecastMakeSaleData::getDayNum177, obj.getDayNum177())
          .eq(Objects.nonNull(obj.getDayNum178()), ApsGoodsForecastMakeSaleData::getDayNum178, obj.getDayNum178())
          .eq(Objects.nonNull(obj.getDayNum179()), ApsGoodsForecastMakeSaleData::getDayNum179, obj.getDayNum179())
          .eq(Objects.nonNull(obj.getDayNum180()), ApsGoodsForecastMakeSaleData::getDayNum180, obj.getDayNum180())
          .eq(Objects.nonNull(obj.getDayNum181()), ApsGoodsForecastMakeSaleData::getDayNum181, obj.getDayNum181())
          .eq(Objects.nonNull(obj.getDayNum182()), ApsGoodsForecastMakeSaleData::getDayNum182, obj.getDayNum182())
          .eq(Objects.nonNull(obj.getDayNum183()), ApsGoodsForecastMakeSaleData::getDayNum183, obj.getDayNum183())
          .eq(Objects.nonNull(obj.getDayNum184()), ApsGoodsForecastMakeSaleData::getDayNum184, obj.getDayNum184())
          .eq(Objects.nonNull(obj.getDayNum185()), ApsGoodsForecastMakeSaleData::getDayNum185, obj.getDayNum185())
          .eq(Objects.nonNull(obj.getDayNum186()), ApsGoodsForecastMakeSaleData::getDayNum186, obj.getDayNum186())
          .eq(Objects.nonNull(obj.getDayNum187()), ApsGoodsForecastMakeSaleData::getDayNum187, obj.getDayNum187())
          .eq(Objects.nonNull(obj.getDayNum188()), ApsGoodsForecastMakeSaleData::getDayNum188, obj.getDayNum188())
          .eq(Objects.nonNull(obj.getDayNum189()), ApsGoodsForecastMakeSaleData::getDayNum189, obj.getDayNum189())
          .eq(Objects.nonNull(obj.getDayNum190()), ApsGoodsForecastMakeSaleData::getDayNum190, obj.getDayNum190())
          .eq(Objects.nonNull(obj.getDayNum191()), ApsGoodsForecastMakeSaleData::getDayNum191, obj.getDayNum191())
          .eq(Objects.nonNull(obj.getDayNum192()), ApsGoodsForecastMakeSaleData::getDayNum192, obj.getDayNum192())
          .eq(Objects.nonNull(obj.getDayNum193()), ApsGoodsForecastMakeSaleData::getDayNum193, obj.getDayNum193())
          .eq(Objects.nonNull(obj.getDayNum194()), ApsGoodsForecastMakeSaleData::getDayNum194, obj.getDayNum194())
          .eq(Objects.nonNull(obj.getDayNum195()), ApsGoodsForecastMakeSaleData::getDayNum195, obj.getDayNum195())
          .eq(Objects.nonNull(obj.getDayNum196()), ApsGoodsForecastMakeSaleData::getDayNum196, obj.getDayNum196())
          .eq(Objects.nonNull(obj.getDayNum197()), ApsGoodsForecastMakeSaleData::getDayNum197, obj.getDayNum197())
          .eq(Objects.nonNull(obj.getDayNum198()), ApsGoodsForecastMakeSaleData::getDayNum198, obj.getDayNum198())
          .eq(Objects.nonNull(obj.getDayNum199()), ApsGoodsForecastMakeSaleData::getDayNum199, obj.getDayNum199())
          .eq(Objects.nonNull(obj.getDayNum200()), ApsGoodsForecastMakeSaleData::getDayNum200, obj.getDayNum200())
          .eq(Objects.nonNull(obj.getDayNum201()), ApsGoodsForecastMakeSaleData::getDayNum201, obj.getDayNum201())
          .eq(Objects.nonNull(obj.getDayNum202()), ApsGoodsForecastMakeSaleData::getDayNum202, obj.getDayNum202())
          .eq(Objects.nonNull(obj.getDayNum203()), ApsGoodsForecastMakeSaleData::getDayNum203, obj.getDayNum203())
          .eq(Objects.nonNull(obj.getDayNum204()), ApsGoodsForecastMakeSaleData::getDayNum204, obj.getDayNum204())
          .eq(Objects.nonNull(obj.getDayNum205()), ApsGoodsForecastMakeSaleData::getDayNum205, obj.getDayNum205())
          .eq(Objects.nonNull(obj.getDayNum206()), ApsGoodsForecastMakeSaleData::getDayNum206, obj.getDayNum206())
          .eq(Objects.nonNull(obj.getDayNum207()), ApsGoodsForecastMakeSaleData::getDayNum207, obj.getDayNum207())
          .eq(Objects.nonNull(obj.getDayNum208()), ApsGoodsForecastMakeSaleData::getDayNum208, obj.getDayNum208())
          .eq(Objects.nonNull(obj.getDayNum209()), ApsGoodsForecastMakeSaleData::getDayNum209, obj.getDayNum209())
          .eq(Objects.nonNull(obj.getDayNum210()), ApsGoodsForecastMakeSaleData::getDayNum210, obj.getDayNum210())
          .eq(Objects.nonNull(obj.getDayNum211()), ApsGoodsForecastMakeSaleData::getDayNum211, obj.getDayNum211())
          .eq(Objects.nonNull(obj.getDayNum212()), ApsGoodsForecastMakeSaleData::getDayNum212, obj.getDayNum212())
          .eq(Objects.nonNull(obj.getDayNum213()), ApsGoodsForecastMakeSaleData::getDayNum213, obj.getDayNum213())
          .eq(Objects.nonNull(obj.getDayNum214()), ApsGoodsForecastMakeSaleData::getDayNum214, obj.getDayNum214())
          .eq(Objects.nonNull(obj.getDayNum215()), ApsGoodsForecastMakeSaleData::getDayNum215, obj.getDayNum215())
          .eq(Objects.nonNull(obj.getDayNum216()), ApsGoodsForecastMakeSaleData::getDayNum216, obj.getDayNum216())
          .eq(Objects.nonNull(obj.getDayNum217()), ApsGoodsForecastMakeSaleData::getDayNum217, obj.getDayNum217())
          .eq(Objects.nonNull(obj.getDayNum218()), ApsGoodsForecastMakeSaleData::getDayNum218, obj.getDayNum218())
          .eq(Objects.nonNull(obj.getDayNum219()), ApsGoodsForecastMakeSaleData::getDayNum219, obj.getDayNum219())
          .eq(Objects.nonNull(obj.getDayNum220()), ApsGoodsForecastMakeSaleData::getDayNum220, obj.getDayNum220())
          .eq(Objects.nonNull(obj.getDayNum221()), ApsGoodsForecastMakeSaleData::getDayNum221, obj.getDayNum221())
          .eq(Objects.nonNull(obj.getDayNum222()), ApsGoodsForecastMakeSaleData::getDayNum222, obj.getDayNum222())
          .eq(Objects.nonNull(obj.getDayNum223()), ApsGoodsForecastMakeSaleData::getDayNum223, obj.getDayNum223())
          .eq(Objects.nonNull(obj.getDayNum224()), ApsGoodsForecastMakeSaleData::getDayNum224, obj.getDayNum224())
          .eq(Objects.nonNull(obj.getDayNum225()), ApsGoodsForecastMakeSaleData::getDayNum225, obj.getDayNum225())
          .eq(Objects.nonNull(obj.getDayNum226()), ApsGoodsForecastMakeSaleData::getDayNum226, obj.getDayNum226())
          .eq(Objects.nonNull(obj.getDayNum227()), ApsGoodsForecastMakeSaleData::getDayNum227, obj.getDayNum227())
          .eq(Objects.nonNull(obj.getDayNum228()), ApsGoodsForecastMakeSaleData::getDayNum228, obj.getDayNum228())
          .eq(Objects.nonNull(obj.getDayNum229()), ApsGoodsForecastMakeSaleData::getDayNum229, obj.getDayNum229())
          .eq(Objects.nonNull(obj.getDayNum230()), ApsGoodsForecastMakeSaleData::getDayNum230, obj.getDayNum230())
          .eq(Objects.nonNull(obj.getDayNum231()), ApsGoodsForecastMakeSaleData::getDayNum231, obj.getDayNum231())
          .eq(Objects.nonNull(obj.getDayNum232()), ApsGoodsForecastMakeSaleData::getDayNum232, obj.getDayNum232())
          .eq(Objects.nonNull(obj.getDayNum233()), ApsGoodsForecastMakeSaleData::getDayNum233, obj.getDayNum233())
          .eq(Objects.nonNull(obj.getDayNum234()), ApsGoodsForecastMakeSaleData::getDayNum234, obj.getDayNum234())
          .eq(Objects.nonNull(obj.getDayNum235()), ApsGoodsForecastMakeSaleData::getDayNum235, obj.getDayNum235())
          .eq(Objects.nonNull(obj.getDayNum236()), ApsGoodsForecastMakeSaleData::getDayNum236, obj.getDayNum236())
          .eq(Objects.nonNull(obj.getDayNum237()), ApsGoodsForecastMakeSaleData::getDayNum237, obj.getDayNum237())
          .eq(Objects.nonNull(obj.getDayNum238()), ApsGoodsForecastMakeSaleData::getDayNum238, obj.getDayNum238())
          .eq(Objects.nonNull(obj.getDayNum239()), ApsGoodsForecastMakeSaleData::getDayNum239, obj.getDayNum239())
          .eq(Objects.nonNull(obj.getDayNum240()), ApsGoodsForecastMakeSaleData::getDayNum240, obj.getDayNum240())
          .eq(Objects.nonNull(obj.getDayNum241()), ApsGoodsForecastMakeSaleData::getDayNum241, obj.getDayNum241())
          .eq(Objects.nonNull(obj.getDayNum242()), ApsGoodsForecastMakeSaleData::getDayNum242, obj.getDayNum242())
          .eq(Objects.nonNull(obj.getDayNum243()), ApsGoodsForecastMakeSaleData::getDayNum243, obj.getDayNum243())
          .eq(Objects.nonNull(obj.getDayNum244()), ApsGoodsForecastMakeSaleData::getDayNum244, obj.getDayNum244())
          .eq(Objects.nonNull(obj.getDayNum245()), ApsGoodsForecastMakeSaleData::getDayNum245, obj.getDayNum245())
          .eq(Objects.nonNull(obj.getDayNum246()), ApsGoodsForecastMakeSaleData::getDayNum246, obj.getDayNum246())
          .eq(Objects.nonNull(obj.getDayNum247()), ApsGoodsForecastMakeSaleData::getDayNum247, obj.getDayNum247())
          .eq(Objects.nonNull(obj.getDayNum248()), ApsGoodsForecastMakeSaleData::getDayNum248, obj.getDayNum248())
          .eq(Objects.nonNull(obj.getDayNum249()), ApsGoodsForecastMakeSaleData::getDayNum249, obj.getDayNum249())
          .eq(Objects.nonNull(obj.getDayNum250()), ApsGoodsForecastMakeSaleData::getDayNum250, obj.getDayNum250())
          .eq(Objects.nonNull(obj.getDayNum251()), ApsGoodsForecastMakeSaleData::getDayNum251, obj.getDayNum251())
          .eq(Objects.nonNull(obj.getDayNum252()), ApsGoodsForecastMakeSaleData::getDayNum252, obj.getDayNum252())
          .eq(Objects.nonNull(obj.getDayNum253()), ApsGoodsForecastMakeSaleData::getDayNum253, obj.getDayNum253())
          .eq(Objects.nonNull(obj.getDayNum254()), ApsGoodsForecastMakeSaleData::getDayNum254, obj.getDayNum254())
          .eq(Objects.nonNull(obj.getDayNum255()), ApsGoodsForecastMakeSaleData::getDayNum255, obj.getDayNum255())
          .eq(Objects.nonNull(obj.getDayNum256()), ApsGoodsForecastMakeSaleData::getDayNum256, obj.getDayNum256())
          .eq(Objects.nonNull(obj.getDayNum257()), ApsGoodsForecastMakeSaleData::getDayNum257, obj.getDayNum257())
          .eq(Objects.nonNull(obj.getDayNum258()), ApsGoodsForecastMakeSaleData::getDayNum258, obj.getDayNum258())
          .eq(Objects.nonNull(obj.getDayNum259()), ApsGoodsForecastMakeSaleData::getDayNum259, obj.getDayNum259())
          .eq(Objects.nonNull(obj.getDayNum260()), ApsGoodsForecastMakeSaleData::getDayNum260, obj.getDayNum260())
          .eq(Objects.nonNull(obj.getDayNum261()), ApsGoodsForecastMakeSaleData::getDayNum261, obj.getDayNum261())
          .eq(Objects.nonNull(obj.getDayNum262()), ApsGoodsForecastMakeSaleData::getDayNum262, obj.getDayNum262())
          .eq(Objects.nonNull(obj.getDayNum263()), ApsGoodsForecastMakeSaleData::getDayNum263, obj.getDayNum263())
          .eq(Objects.nonNull(obj.getDayNum264()), ApsGoodsForecastMakeSaleData::getDayNum264, obj.getDayNum264())
          .eq(Objects.nonNull(obj.getDayNum265()), ApsGoodsForecastMakeSaleData::getDayNum265, obj.getDayNum265())
          .eq(Objects.nonNull(obj.getDayNum266()), ApsGoodsForecastMakeSaleData::getDayNum266, obj.getDayNum266())
          .eq(Objects.nonNull(obj.getDayNum267()), ApsGoodsForecastMakeSaleData::getDayNum267, obj.getDayNum267())
          .eq(Objects.nonNull(obj.getDayNum268()), ApsGoodsForecastMakeSaleData::getDayNum268, obj.getDayNum268())
          .eq(Objects.nonNull(obj.getDayNum269()), ApsGoodsForecastMakeSaleData::getDayNum269, obj.getDayNum269())
          .eq(Objects.nonNull(obj.getDayNum270()), ApsGoodsForecastMakeSaleData::getDayNum270, obj.getDayNum270())
          .eq(Objects.nonNull(obj.getDayNum271()), ApsGoodsForecastMakeSaleData::getDayNum271, obj.getDayNum271())
          .eq(Objects.nonNull(obj.getDayNum272()), ApsGoodsForecastMakeSaleData::getDayNum272, obj.getDayNum272())
          .eq(Objects.nonNull(obj.getDayNum273()), ApsGoodsForecastMakeSaleData::getDayNum273, obj.getDayNum273())
          .eq(Objects.nonNull(obj.getDayNum274()), ApsGoodsForecastMakeSaleData::getDayNum274, obj.getDayNum274())
          .eq(Objects.nonNull(obj.getDayNum275()), ApsGoodsForecastMakeSaleData::getDayNum275, obj.getDayNum275())
          .eq(Objects.nonNull(obj.getDayNum276()), ApsGoodsForecastMakeSaleData::getDayNum276, obj.getDayNum276())
          .eq(Objects.nonNull(obj.getDayNum277()), ApsGoodsForecastMakeSaleData::getDayNum277, obj.getDayNum277())
          .eq(Objects.nonNull(obj.getDayNum278()), ApsGoodsForecastMakeSaleData::getDayNum278, obj.getDayNum278())
          .eq(Objects.nonNull(obj.getDayNum279()), ApsGoodsForecastMakeSaleData::getDayNum279, obj.getDayNum279())
          .eq(Objects.nonNull(obj.getDayNum280()), ApsGoodsForecastMakeSaleData::getDayNum280, obj.getDayNum280())
          .eq(Objects.nonNull(obj.getDayNum281()), ApsGoodsForecastMakeSaleData::getDayNum281, obj.getDayNum281())
          .eq(Objects.nonNull(obj.getDayNum282()), ApsGoodsForecastMakeSaleData::getDayNum282, obj.getDayNum282())
          .eq(Objects.nonNull(obj.getDayNum283()), ApsGoodsForecastMakeSaleData::getDayNum283, obj.getDayNum283())
          .eq(Objects.nonNull(obj.getDayNum284()), ApsGoodsForecastMakeSaleData::getDayNum284, obj.getDayNum284())
          .eq(Objects.nonNull(obj.getDayNum285()), ApsGoodsForecastMakeSaleData::getDayNum285, obj.getDayNum285())
          .eq(Objects.nonNull(obj.getDayNum286()), ApsGoodsForecastMakeSaleData::getDayNum286, obj.getDayNum286())
          .eq(Objects.nonNull(obj.getDayNum287()), ApsGoodsForecastMakeSaleData::getDayNum287, obj.getDayNum287())
          .eq(Objects.nonNull(obj.getDayNum288()), ApsGoodsForecastMakeSaleData::getDayNum288, obj.getDayNum288())
          .eq(Objects.nonNull(obj.getDayNum289()), ApsGoodsForecastMakeSaleData::getDayNum289, obj.getDayNum289())
          .eq(Objects.nonNull(obj.getDayNum290()), ApsGoodsForecastMakeSaleData::getDayNum290, obj.getDayNum290())
          .eq(Objects.nonNull(obj.getDayNum291()), ApsGoodsForecastMakeSaleData::getDayNum291, obj.getDayNum291())
          .eq(Objects.nonNull(obj.getDayNum292()), ApsGoodsForecastMakeSaleData::getDayNum292, obj.getDayNum292())
          .eq(Objects.nonNull(obj.getDayNum293()), ApsGoodsForecastMakeSaleData::getDayNum293, obj.getDayNum293())
          .eq(Objects.nonNull(obj.getDayNum294()), ApsGoodsForecastMakeSaleData::getDayNum294, obj.getDayNum294())
          .eq(Objects.nonNull(obj.getDayNum295()), ApsGoodsForecastMakeSaleData::getDayNum295, obj.getDayNum295())
          .eq(Objects.nonNull(obj.getDayNum296()), ApsGoodsForecastMakeSaleData::getDayNum296, obj.getDayNum296())
          .eq(Objects.nonNull(obj.getDayNum297()), ApsGoodsForecastMakeSaleData::getDayNum297, obj.getDayNum297())
          .eq(Objects.nonNull(obj.getDayNum298()), ApsGoodsForecastMakeSaleData::getDayNum298, obj.getDayNum298())
          .eq(Objects.nonNull(obj.getDayNum299()), ApsGoodsForecastMakeSaleData::getDayNum299, obj.getDayNum299())
          .eq(Objects.nonNull(obj.getDayNum300()), ApsGoodsForecastMakeSaleData::getDayNum300, obj.getDayNum300())
          .eq(Objects.nonNull(obj.getDayNum301()), ApsGoodsForecastMakeSaleData::getDayNum301, obj.getDayNum301())
          .eq(Objects.nonNull(obj.getDayNum302()), ApsGoodsForecastMakeSaleData::getDayNum302, obj.getDayNum302())
          .eq(Objects.nonNull(obj.getDayNum303()), ApsGoodsForecastMakeSaleData::getDayNum303, obj.getDayNum303())
          .eq(Objects.nonNull(obj.getDayNum304()), ApsGoodsForecastMakeSaleData::getDayNum304, obj.getDayNum304())
          .eq(Objects.nonNull(obj.getDayNum305()), ApsGoodsForecastMakeSaleData::getDayNum305, obj.getDayNum305())
          .eq(Objects.nonNull(obj.getDayNum306()), ApsGoodsForecastMakeSaleData::getDayNum306, obj.getDayNum306())
          .eq(Objects.nonNull(obj.getDayNum307()), ApsGoodsForecastMakeSaleData::getDayNum307, obj.getDayNum307())
          .eq(Objects.nonNull(obj.getDayNum308()), ApsGoodsForecastMakeSaleData::getDayNum308, obj.getDayNum308())
          .eq(Objects.nonNull(obj.getDayNum309()), ApsGoodsForecastMakeSaleData::getDayNum309, obj.getDayNum309())
          .eq(Objects.nonNull(obj.getDayNum310()), ApsGoodsForecastMakeSaleData::getDayNum310, obj.getDayNum310())
          .eq(Objects.nonNull(obj.getDayNum311()), ApsGoodsForecastMakeSaleData::getDayNum311, obj.getDayNum311())
          .eq(Objects.nonNull(obj.getDayNum312()), ApsGoodsForecastMakeSaleData::getDayNum312, obj.getDayNum312())
          .eq(Objects.nonNull(obj.getDayNum313()), ApsGoodsForecastMakeSaleData::getDayNum313, obj.getDayNum313())
          .eq(Objects.nonNull(obj.getDayNum314()), ApsGoodsForecastMakeSaleData::getDayNum314, obj.getDayNum314())
          .eq(Objects.nonNull(obj.getDayNum315()), ApsGoodsForecastMakeSaleData::getDayNum315, obj.getDayNum315())
          .eq(Objects.nonNull(obj.getDayNum316()), ApsGoodsForecastMakeSaleData::getDayNum316, obj.getDayNum316())
          .eq(Objects.nonNull(obj.getDayNum317()), ApsGoodsForecastMakeSaleData::getDayNum317, obj.getDayNum317())
          .eq(Objects.nonNull(obj.getDayNum318()), ApsGoodsForecastMakeSaleData::getDayNum318, obj.getDayNum318())
          .eq(Objects.nonNull(obj.getDayNum319()), ApsGoodsForecastMakeSaleData::getDayNum319, obj.getDayNum319())
          .eq(Objects.nonNull(obj.getDayNum320()), ApsGoodsForecastMakeSaleData::getDayNum320, obj.getDayNum320())
          .eq(Objects.nonNull(obj.getDayNum321()), ApsGoodsForecastMakeSaleData::getDayNum321, obj.getDayNum321())
          .eq(Objects.nonNull(obj.getDayNum322()), ApsGoodsForecastMakeSaleData::getDayNum322, obj.getDayNum322())
          .eq(Objects.nonNull(obj.getDayNum323()), ApsGoodsForecastMakeSaleData::getDayNum323, obj.getDayNum323())
          .eq(Objects.nonNull(obj.getDayNum324()), ApsGoodsForecastMakeSaleData::getDayNum324, obj.getDayNum324())
          .eq(Objects.nonNull(obj.getDayNum325()), ApsGoodsForecastMakeSaleData::getDayNum325, obj.getDayNum325())
          .eq(Objects.nonNull(obj.getDayNum326()), ApsGoodsForecastMakeSaleData::getDayNum326, obj.getDayNum326())
          .eq(Objects.nonNull(obj.getDayNum327()), ApsGoodsForecastMakeSaleData::getDayNum327, obj.getDayNum327())
          .eq(Objects.nonNull(obj.getDayNum328()), ApsGoodsForecastMakeSaleData::getDayNum328, obj.getDayNum328())
          .eq(Objects.nonNull(obj.getDayNum329()), ApsGoodsForecastMakeSaleData::getDayNum329, obj.getDayNum329())
          .eq(Objects.nonNull(obj.getDayNum330()), ApsGoodsForecastMakeSaleData::getDayNum330, obj.getDayNum330())
          .eq(Objects.nonNull(obj.getDayNum331()), ApsGoodsForecastMakeSaleData::getDayNum331, obj.getDayNum331())
          .eq(Objects.nonNull(obj.getDayNum332()), ApsGoodsForecastMakeSaleData::getDayNum332, obj.getDayNum332())
          .eq(Objects.nonNull(obj.getDayNum333()), ApsGoodsForecastMakeSaleData::getDayNum333, obj.getDayNum333())
          .eq(Objects.nonNull(obj.getDayNum334()), ApsGoodsForecastMakeSaleData::getDayNum334, obj.getDayNum334())
          .eq(Objects.nonNull(obj.getDayNum335()), ApsGoodsForecastMakeSaleData::getDayNum335, obj.getDayNum335())
          .eq(Objects.nonNull(obj.getDayNum336()), ApsGoodsForecastMakeSaleData::getDayNum336, obj.getDayNum336())
          .eq(Objects.nonNull(obj.getDayNum337()), ApsGoodsForecastMakeSaleData::getDayNum337, obj.getDayNum337())
          .eq(Objects.nonNull(obj.getDayNum338()), ApsGoodsForecastMakeSaleData::getDayNum338, obj.getDayNum338())
          .eq(Objects.nonNull(obj.getDayNum339()), ApsGoodsForecastMakeSaleData::getDayNum339, obj.getDayNum339())
          .eq(Objects.nonNull(obj.getDayNum340()), ApsGoodsForecastMakeSaleData::getDayNum340, obj.getDayNum340())
          .eq(Objects.nonNull(obj.getDayNum341()), ApsGoodsForecastMakeSaleData::getDayNum341, obj.getDayNum341())
          .eq(Objects.nonNull(obj.getDayNum342()), ApsGoodsForecastMakeSaleData::getDayNum342, obj.getDayNum342())
          .eq(Objects.nonNull(obj.getDayNum343()), ApsGoodsForecastMakeSaleData::getDayNum343, obj.getDayNum343())
          .eq(Objects.nonNull(obj.getDayNum344()), ApsGoodsForecastMakeSaleData::getDayNum344, obj.getDayNum344())
          .eq(Objects.nonNull(obj.getDayNum345()), ApsGoodsForecastMakeSaleData::getDayNum345, obj.getDayNum345())
          .eq(Objects.nonNull(obj.getDayNum346()), ApsGoodsForecastMakeSaleData::getDayNum346, obj.getDayNum346())
          .eq(Objects.nonNull(obj.getDayNum347()), ApsGoodsForecastMakeSaleData::getDayNum347, obj.getDayNum347())
          .eq(Objects.nonNull(obj.getDayNum348()), ApsGoodsForecastMakeSaleData::getDayNum348, obj.getDayNum348())
          .eq(Objects.nonNull(obj.getDayNum349()), ApsGoodsForecastMakeSaleData::getDayNum349, obj.getDayNum349())
          .eq(Objects.nonNull(obj.getDayNum350()), ApsGoodsForecastMakeSaleData::getDayNum350, obj.getDayNum350())
          .eq(Objects.nonNull(obj.getDayNum351()), ApsGoodsForecastMakeSaleData::getDayNum351, obj.getDayNum351())
          .eq(Objects.nonNull(obj.getDayNum352()), ApsGoodsForecastMakeSaleData::getDayNum352, obj.getDayNum352())
          .eq(Objects.nonNull(obj.getDayNum353()), ApsGoodsForecastMakeSaleData::getDayNum353, obj.getDayNum353())
          .eq(Objects.nonNull(obj.getDayNum354()), ApsGoodsForecastMakeSaleData::getDayNum354, obj.getDayNum354())
          .eq(Objects.nonNull(obj.getDayNum355()), ApsGoodsForecastMakeSaleData::getDayNum355, obj.getDayNum355())
          .eq(Objects.nonNull(obj.getDayNum356()), ApsGoodsForecastMakeSaleData::getDayNum356, obj.getDayNum356())
          .eq(Objects.nonNull(obj.getDayNum357()), ApsGoodsForecastMakeSaleData::getDayNum357, obj.getDayNum357())
          .eq(Objects.nonNull(obj.getDayNum358()), ApsGoodsForecastMakeSaleData::getDayNum358, obj.getDayNum358())
          .eq(Objects.nonNull(obj.getDayNum359()), ApsGoodsForecastMakeSaleData::getDayNum359, obj.getDayNum359())
          .eq(Objects.nonNull(obj.getDayNum360()), ApsGoodsForecastMakeSaleData::getDayNum360, obj.getDayNum360())
          .eq(Objects.nonNull(obj.getDayNum361()), ApsGoodsForecastMakeSaleData::getDayNum361, obj.getDayNum361())
          .eq(Objects.nonNull(obj.getDayNum362()), ApsGoodsForecastMakeSaleData::getDayNum362, obj.getDayNum362())
          .eq(Objects.nonNull(obj.getDayNum363()), ApsGoodsForecastMakeSaleData::getDayNum363, obj.getDayNum363())
          .eq(Objects.nonNull(obj.getDayNum364()), ApsGoodsForecastMakeSaleData::getDayNum364, obj.getDayNum364())
          .eq(Objects.nonNull(obj.getDayNum365()), ApsGoodsForecastMakeSaleData::getDayNum365, obj.getDayNum365())
          .eq(Objects.nonNull(obj.getDayNum366()), ApsGoodsForecastMakeSaleData::getDayNum366, obj.getDayNum366())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMakeSaleData::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getMonth()), ApsGoodsForecastMakeSaleData::getMonth, obj.getMonth())
          .eq(Objects.nonNull(obj.getMonths()), ApsGoodsForecastMakeSaleData::getMonths, obj.getMonths())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMakeSaleData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMakeSaleData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMakeSaleDataService#queryPageList");

  }


}

