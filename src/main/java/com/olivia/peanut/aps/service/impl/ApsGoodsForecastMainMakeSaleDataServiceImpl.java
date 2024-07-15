package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMainMakeSaleDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainMakeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainMakeSaleDataService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (ApsGoodsForecastMainMakeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-08 09:52:51
 */
@Service("apsGoodsForecastMainMakeSaleDataService")
@Transactional
public class ApsGoodsForecastMainMakeSaleDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMainMakeSaleDataMapper, ApsGoodsForecastMainMakeSaleData> implements
    ApsGoodsForecastMainMakeSaleDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMainMakeSaleDataQueryListRes queryList(ApsGoodsForecastMainMakeSaleDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainMakeSaleData> list = this.list(q);

    List<ApsGoodsForecastMainMakeSaleDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMainMakeSaleDataDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);

//    ((ApsGoodsForecastMainMakeSaleDataServiceImpl) AopContext.currentProxy()).setName(dataList);
    return new ApsGoodsForecastMainMakeSaleDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainMakeSaleDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMainMakeSaleData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMainMakeSaleData> list = this.page(page, q);
      IPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
//    ((ApsGoodsForecastMainMakeSaleDataServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMainMakeSaleDataDto> apsGoodsForecastMainMakeSaleDataDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMainMakeSaleDataDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> getWrapper(ApsGoodsForecastMainMakeSaleDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMainMakeSaleData::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMainMakeId()), ApsGoodsForecastMainMakeSaleData::getMainMakeId, obj.getMainMakeId())
          .eq(StringUtils.isNoneBlank(obj.getSaleConfigCode()), ApsGoodsForecastMainMakeSaleData::getSaleConfigCode, obj.getSaleConfigCode())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMainMakeSaleData::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getDayNum1()), ApsGoodsForecastMainMakeSaleData::getDayNum1, obj.getDayNum1())
          .eq(Objects.nonNull(obj.getDayNum2()), ApsGoodsForecastMainMakeSaleData::getDayNum2, obj.getDayNum2())
          .eq(Objects.nonNull(obj.getDayNum3()), ApsGoodsForecastMainMakeSaleData::getDayNum3, obj.getDayNum3())
          .eq(Objects.nonNull(obj.getDayNum4()), ApsGoodsForecastMainMakeSaleData::getDayNum4, obj.getDayNum4())
          .eq(Objects.nonNull(obj.getDayNum5()), ApsGoodsForecastMainMakeSaleData::getDayNum5, obj.getDayNum5())
          .eq(Objects.nonNull(obj.getDayNum6()), ApsGoodsForecastMainMakeSaleData::getDayNum6, obj.getDayNum6())
          .eq(Objects.nonNull(obj.getDayNum7()), ApsGoodsForecastMainMakeSaleData::getDayNum7, obj.getDayNum7())
          .eq(Objects.nonNull(obj.getDayNum8()), ApsGoodsForecastMainMakeSaleData::getDayNum8, obj.getDayNum8())
          .eq(Objects.nonNull(obj.getDayNum9()), ApsGoodsForecastMainMakeSaleData::getDayNum9, obj.getDayNum9())
          .eq(Objects.nonNull(obj.getDayNum10()), ApsGoodsForecastMainMakeSaleData::getDayNum10, obj.getDayNum10())
          .eq(Objects.nonNull(obj.getDayNum11()), ApsGoodsForecastMainMakeSaleData::getDayNum11, obj.getDayNum11())
          .eq(Objects.nonNull(obj.getDayNum12()), ApsGoodsForecastMainMakeSaleData::getDayNum12, obj.getDayNum12())
          .eq(Objects.nonNull(obj.getDayNum13()), ApsGoodsForecastMainMakeSaleData::getDayNum13, obj.getDayNum13())
          .eq(Objects.nonNull(obj.getDayNum14()), ApsGoodsForecastMainMakeSaleData::getDayNum14, obj.getDayNum14())
          .eq(Objects.nonNull(obj.getDayNum15()), ApsGoodsForecastMainMakeSaleData::getDayNum15, obj.getDayNum15())
          .eq(Objects.nonNull(obj.getDayNum16()), ApsGoodsForecastMainMakeSaleData::getDayNum16, obj.getDayNum16())
          .eq(Objects.nonNull(obj.getDayNum17()), ApsGoodsForecastMainMakeSaleData::getDayNum17, obj.getDayNum17())
          .eq(Objects.nonNull(obj.getDayNum18()), ApsGoodsForecastMainMakeSaleData::getDayNum18, obj.getDayNum18())
          .eq(Objects.nonNull(obj.getDayNum19()), ApsGoodsForecastMainMakeSaleData::getDayNum19, obj.getDayNum19())
          .eq(Objects.nonNull(obj.getDayNum20()), ApsGoodsForecastMainMakeSaleData::getDayNum20, obj.getDayNum20())
          .eq(Objects.nonNull(obj.getDayNum21()), ApsGoodsForecastMainMakeSaleData::getDayNum21, obj.getDayNum21())
          .eq(Objects.nonNull(obj.getDayNum22()), ApsGoodsForecastMainMakeSaleData::getDayNum22, obj.getDayNum22())
          .eq(Objects.nonNull(obj.getDayNum23()), ApsGoodsForecastMainMakeSaleData::getDayNum23, obj.getDayNum23())
          .eq(Objects.nonNull(obj.getDayNum24()), ApsGoodsForecastMainMakeSaleData::getDayNum24, obj.getDayNum24())
          .eq(Objects.nonNull(obj.getDayNum25()), ApsGoodsForecastMainMakeSaleData::getDayNum25, obj.getDayNum25())
          .eq(Objects.nonNull(obj.getDayNum26()), ApsGoodsForecastMainMakeSaleData::getDayNum26, obj.getDayNum26())
          .eq(Objects.nonNull(obj.getDayNum27()), ApsGoodsForecastMainMakeSaleData::getDayNum27, obj.getDayNum27())
          .eq(Objects.nonNull(obj.getDayNum28()), ApsGoodsForecastMainMakeSaleData::getDayNum28, obj.getDayNum28())
          .eq(Objects.nonNull(obj.getDayNum29()), ApsGoodsForecastMainMakeSaleData::getDayNum29, obj.getDayNum29())
          .eq(Objects.nonNull(obj.getDayNum30()), ApsGoodsForecastMainMakeSaleData::getDayNum30, obj.getDayNum30())
          .eq(Objects.nonNull(obj.getDayNum31()), ApsGoodsForecastMainMakeSaleData::getDayNum31, obj.getDayNum31())
          .eq(Objects.nonNull(obj.getDayNum32()), ApsGoodsForecastMainMakeSaleData::getDayNum32, obj.getDayNum32())
          .eq(Objects.nonNull(obj.getDayNum33()), ApsGoodsForecastMainMakeSaleData::getDayNum33, obj.getDayNum33())
          .eq(Objects.nonNull(obj.getDayNum34()), ApsGoodsForecastMainMakeSaleData::getDayNum34, obj.getDayNum34())
          .eq(Objects.nonNull(obj.getDayNum35()), ApsGoodsForecastMainMakeSaleData::getDayNum35, obj.getDayNum35())
          .eq(Objects.nonNull(obj.getDayNum36()), ApsGoodsForecastMainMakeSaleData::getDayNum36, obj.getDayNum36())
          .eq(Objects.nonNull(obj.getDayNum37()), ApsGoodsForecastMainMakeSaleData::getDayNum37, obj.getDayNum37())
          .eq(Objects.nonNull(obj.getDayNum38()), ApsGoodsForecastMainMakeSaleData::getDayNum38, obj.getDayNum38())
          .eq(Objects.nonNull(obj.getDayNum39()), ApsGoodsForecastMainMakeSaleData::getDayNum39, obj.getDayNum39())
          .eq(Objects.nonNull(obj.getDayNum40()), ApsGoodsForecastMainMakeSaleData::getDayNum40, obj.getDayNum40())
          .eq(Objects.nonNull(obj.getDayNum41()), ApsGoodsForecastMainMakeSaleData::getDayNum41, obj.getDayNum41())
          .eq(Objects.nonNull(obj.getDayNum42()), ApsGoodsForecastMainMakeSaleData::getDayNum42, obj.getDayNum42())
          .eq(Objects.nonNull(obj.getDayNum43()), ApsGoodsForecastMainMakeSaleData::getDayNum43, obj.getDayNum43())
          .eq(Objects.nonNull(obj.getDayNum44()), ApsGoodsForecastMainMakeSaleData::getDayNum44, obj.getDayNum44())
          .eq(Objects.nonNull(obj.getDayNum45()), ApsGoodsForecastMainMakeSaleData::getDayNum45, obj.getDayNum45())
          .eq(Objects.nonNull(obj.getDayNum46()), ApsGoodsForecastMainMakeSaleData::getDayNum46, obj.getDayNum46())
          .eq(Objects.nonNull(obj.getDayNum47()), ApsGoodsForecastMainMakeSaleData::getDayNum47, obj.getDayNum47())
          .eq(Objects.nonNull(obj.getDayNum48()), ApsGoodsForecastMainMakeSaleData::getDayNum48, obj.getDayNum48())
          .eq(Objects.nonNull(obj.getDayNum49()), ApsGoodsForecastMainMakeSaleData::getDayNum49, obj.getDayNum49())
          .eq(Objects.nonNull(obj.getDayNum50()), ApsGoodsForecastMainMakeSaleData::getDayNum50, obj.getDayNum50())
          .eq(Objects.nonNull(obj.getDayNum51()), ApsGoodsForecastMainMakeSaleData::getDayNum51, obj.getDayNum51())
          .eq(Objects.nonNull(obj.getDayNum52()), ApsGoodsForecastMainMakeSaleData::getDayNum52, obj.getDayNum52())
          .eq(Objects.nonNull(obj.getDayNum53()), ApsGoodsForecastMainMakeSaleData::getDayNum53, obj.getDayNum53())
          .eq(Objects.nonNull(obj.getDayNum54()), ApsGoodsForecastMainMakeSaleData::getDayNum54, obj.getDayNum54())
          .eq(Objects.nonNull(obj.getDayNum55()), ApsGoodsForecastMainMakeSaleData::getDayNum55, obj.getDayNum55())
          .eq(Objects.nonNull(obj.getDayNum56()), ApsGoodsForecastMainMakeSaleData::getDayNum56, obj.getDayNum56())
          .eq(Objects.nonNull(obj.getDayNum57()), ApsGoodsForecastMainMakeSaleData::getDayNum57, obj.getDayNum57())
          .eq(Objects.nonNull(obj.getDayNum58()), ApsGoodsForecastMainMakeSaleData::getDayNum58, obj.getDayNum58())
          .eq(Objects.nonNull(obj.getDayNum59()), ApsGoodsForecastMainMakeSaleData::getDayNum59, obj.getDayNum59())
          .eq(Objects.nonNull(obj.getDayNum60()), ApsGoodsForecastMainMakeSaleData::getDayNum60, obj.getDayNum60())
          .eq(Objects.nonNull(obj.getDayNum61()), ApsGoodsForecastMainMakeSaleData::getDayNum61, obj.getDayNum61())
          .eq(Objects.nonNull(obj.getDayNum62()), ApsGoodsForecastMainMakeSaleData::getDayNum62, obj.getDayNum62())
          .eq(Objects.nonNull(obj.getDayNum63()), ApsGoodsForecastMainMakeSaleData::getDayNum63, obj.getDayNum63())
          .eq(Objects.nonNull(obj.getDayNum64()), ApsGoodsForecastMainMakeSaleData::getDayNum64, obj.getDayNum64())
          .eq(Objects.nonNull(obj.getDayNum65()), ApsGoodsForecastMainMakeSaleData::getDayNum65, obj.getDayNum65())
          .eq(Objects.nonNull(obj.getDayNum66()), ApsGoodsForecastMainMakeSaleData::getDayNum66, obj.getDayNum66())
          .eq(Objects.nonNull(obj.getDayNum67()), ApsGoodsForecastMainMakeSaleData::getDayNum67, obj.getDayNum67())
          .eq(Objects.nonNull(obj.getDayNum68()), ApsGoodsForecastMainMakeSaleData::getDayNum68, obj.getDayNum68())
          .eq(Objects.nonNull(obj.getDayNum69()), ApsGoodsForecastMainMakeSaleData::getDayNum69, obj.getDayNum69())
          .eq(Objects.nonNull(obj.getDayNum70()), ApsGoodsForecastMainMakeSaleData::getDayNum70, obj.getDayNum70())
          .eq(Objects.nonNull(obj.getDayNum71()), ApsGoodsForecastMainMakeSaleData::getDayNum71, obj.getDayNum71())
          .eq(Objects.nonNull(obj.getDayNum72()), ApsGoodsForecastMainMakeSaleData::getDayNum72, obj.getDayNum72())
          .eq(Objects.nonNull(obj.getDayNum73()), ApsGoodsForecastMainMakeSaleData::getDayNum73, obj.getDayNum73())
          .eq(Objects.nonNull(obj.getDayNum74()), ApsGoodsForecastMainMakeSaleData::getDayNum74, obj.getDayNum74())
          .eq(Objects.nonNull(obj.getDayNum75()), ApsGoodsForecastMainMakeSaleData::getDayNum75, obj.getDayNum75())
          .eq(Objects.nonNull(obj.getDayNum76()), ApsGoodsForecastMainMakeSaleData::getDayNum76, obj.getDayNum76())
          .eq(Objects.nonNull(obj.getDayNum77()), ApsGoodsForecastMainMakeSaleData::getDayNum77, obj.getDayNum77())
          .eq(Objects.nonNull(obj.getDayNum78()), ApsGoodsForecastMainMakeSaleData::getDayNum78, obj.getDayNum78())
          .eq(Objects.nonNull(obj.getDayNum79()), ApsGoodsForecastMainMakeSaleData::getDayNum79, obj.getDayNum79())
          .eq(Objects.nonNull(obj.getDayNum80()), ApsGoodsForecastMainMakeSaleData::getDayNum80, obj.getDayNum80())
          .eq(Objects.nonNull(obj.getDayNum81()), ApsGoodsForecastMainMakeSaleData::getDayNum81, obj.getDayNum81())
          .eq(Objects.nonNull(obj.getDayNum82()), ApsGoodsForecastMainMakeSaleData::getDayNum82, obj.getDayNum82())
          .eq(Objects.nonNull(obj.getDayNum83()), ApsGoodsForecastMainMakeSaleData::getDayNum83, obj.getDayNum83())
          .eq(Objects.nonNull(obj.getDayNum84()), ApsGoodsForecastMainMakeSaleData::getDayNum84, obj.getDayNum84())
          .eq(Objects.nonNull(obj.getDayNum85()), ApsGoodsForecastMainMakeSaleData::getDayNum85, obj.getDayNum85())
          .eq(Objects.nonNull(obj.getDayNum86()), ApsGoodsForecastMainMakeSaleData::getDayNum86, obj.getDayNum86())
          .eq(Objects.nonNull(obj.getDayNum87()), ApsGoodsForecastMainMakeSaleData::getDayNum87, obj.getDayNum87())
          .eq(Objects.nonNull(obj.getDayNum88()), ApsGoodsForecastMainMakeSaleData::getDayNum88, obj.getDayNum88())
          .eq(Objects.nonNull(obj.getDayNum89()), ApsGoodsForecastMainMakeSaleData::getDayNum89, obj.getDayNum89())
          .eq(Objects.nonNull(obj.getDayNum90()), ApsGoodsForecastMainMakeSaleData::getDayNum90, obj.getDayNum90())
          .eq(Objects.nonNull(obj.getDayNum91()), ApsGoodsForecastMainMakeSaleData::getDayNum91, obj.getDayNum91())
          .eq(Objects.nonNull(obj.getDayNum92()), ApsGoodsForecastMainMakeSaleData::getDayNum92, obj.getDayNum92())
          .eq(Objects.nonNull(obj.getDayNum93()), ApsGoodsForecastMainMakeSaleData::getDayNum93, obj.getDayNum93())
          .eq(Objects.nonNull(obj.getDayNum94()), ApsGoodsForecastMainMakeSaleData::getDayNum94, obj.getDayNum94())
          .eq(Objects.nonNull(obj.getDayNum95()), ApsGoodsForecastMainMakeSaleData::getDayNum95, obj.getDayNum95())
          .eq(Objects.nonNull(obj.getDayNum96()), ApsGoodsForecastMainMakeSaleData::getDayNum96, obj.getDayNum96())
          .eq(Objects.nonNull(obj.getDayNum97()), ApsGoodsForecastMainMakeSaleData::getDayNum97, obj.getDayNum97())
          .eq(Objects.nonNull(obj.getDayNum98()), ApsGoodsForecastMainMakeSaleData::getDayNum98, obj.getDayNum98())
          .eq(Objects.nonNull(obj.getDayNum99()), ApsGoodsForecastMainMakeSaleData::getDayNum99, obj.getDayNum99())
          .eq(Objects.nonNull(obj.getDayNum100()), ApsGoodsForecastMainMakeSaleData::getDayNum100, obj.getDayNum100())
          .eq(Objects.nonNull(obj.getDayNum101()), ApsGoodsForecastMainMakeSaleData::getDayNum101, obj.getDayNum101())
          .eq(Objects.nonNull(obj.getDayNum102()), ApsGoodsForecastMainMakeSaleData::getDayNum102, obj.getDayNum102())
          .eq(Objects.nonNull(obj.getDayNum103()), ApsGoodsForecastMainMakeSaleData::getDayNum103, obj.getDayNum103())
          .eq(Objects.nonNull(obj.getDayNum104()), ApsGoodsForecastMainMakeSaleData::getDayNum104, obj.getDayNum104())
          .eq(Objects.nonNull(obj.getDayNum105()), ApsGoodsForecastMainMakeSaleData::getDayNum105, obj.getDayNum105())
          .eq(Objects.nonNull(obj.getDayNum106()), ApsGoodsForecastMainMakeSaleData::getDayNum106, obj.getDayNum106())
          .eq(Objects.nonNull(obj.getDayNum107()), ApsGoodsForecastMainMakeSaleData::getDayNum107, obj.getDayNum107())
          .eq(Objects.nonNull(obj.getDayNum108()), ApsGoodsForecastMainMakeSaleData::getDayNum108, obj.getDayNum108())
          .eq(Objects.nonNull(obj.getDayNum109()), ApsGoodsForecastMainMakeSaleData::getDayNum109, obj.getDayNum109())
          .eq(Objects.nonNull(obj.getDayNum110()), ApsGoodsForecastMainMakeSaleData::getDayNum110, obj.getDayNum110())
          .eq(Objects.nonNull(obj.getDayNum111()), ApsGoodsForecastMainMakeSaleData::getDayNum111, obj.getDayNum111())
          .eq(Objects.nonNull(obj.getDayNum112()), ApsGoodsForecastMainMakeSaleData::getDayNum112, obj.getDayNum112())
          .eq(Objects.nonNull(obj.getDayNum113()), ApsGoodsForecastMainMakeSaleData::getDayNum113, obj.getDayNum113())
          .eq(Objects.nonNull(obj.getDayNum114()), ApsGoodsForecastMainMakeSaleData::getDayNum114, obj.getDayNum114())
          .eq(Objects.nonNull(obj.getDayNum115()), ApsGoodsForecastMainMakeSaleData::getDayNum115, obj.getDayNum115())
          .eq(Objects.nonNull(obj.getDayNum116()), ApsGoodsForecastMainMakeSaleData::getDayNum116, obj.getDayNum116())
          .eq(Objects.nonNull(obj.getDayNum117()), ApsGoodsForecastMainMakeSaleData::getDayNum117, obj.getDayNum117())
          .eq(Objects.nonNull(obj.getDayNum118()), ApsGoodsForecastMainMakeSaleData::getDayNum118, obj.getDayNum118())
          .eq(Objects.nonNull(obj.getDayNum119()), ApsGoodsForecastMainMakeSaleData::getDayNum119, obj.getDayNum119())
          .eq(Objects.nonNull(obj.getDayNum120()), ApsGoodsForecastMainMakeSaleData::getDayNum120, obj.getDayNum120())
          .eq(Objects.nonNull(obj.getDayNum121()), ApsGoodsForecastMainMakeSaleData::getDayNum121, obj.getDayNum121())
          .eq(Objects.nonNull(obj.getDayNum122()), ApsGoodsForecastMainMakeSaleData::getDayNum122, obj.getDayNum122())
          .eq(Objects.nonNull(obj.getDayNum123()), ApsGoodsForecastMainMakeSaleData::getDayNum123, obj.getDayNum123())
          .eq(Objects.nonNull(obj.getDayNum124()), ApsGoodsForecastMainMakeSaleData::getDayNum124, obj.getDayNum124())
          .eq(Objects.nonNull(obj.getDayNum125()), ApsGoodsForecastMainMakeSaleData::getDayNum125, obj.getDayNum125())
          .eq(Objects.nonNull(obj.getDayNum126()), ApsGoodsForecastMainMakeSaleData::getDayNum126, obj.getDayNum126())
          .eq(Objects.nonNull(obj.getDayNum127()), ApsGoodsForecastMainMakeSaleData::getDayNum127, obj.getDayNum127())
          .eq(Objects.nonNull(obj.getDayNum128()), ApsGoodsForecastMainMakeSaleData::getDayNum128, obj.getDayNum128())
          .eq(Objects.nonNull(obj.getDayNum129()), ApsGoodsForecastMainMakeSaleData::getDayNum129, obj.getDayNum129())
          .eq(Objects.nonNull(obj.getDayNum130()), ApsGoodsForecastMainMakeSaleData::getDayNum130, obj.getDayNum130())
          .eq(Objects.nonNull(obj.getDayNum131()), ApsGoodsForecastMainMakeSaleData::getDayNum131, obj.getDayNum131())
          .eq(Objects.nonNull(obj.getDayNum132()), ApsGoodsForecastMainMakeSaleData::getDayNum132, obj.getDayNum132())
          .eq(Objects.nonNull(obj.getDayNum133()), ApsGoodsForecastMainMakeSaleData::getDayNum133, obj.getDayNum133())
          .eq(Objects.nonNull(obj.getDayNum134()), ApsGoodsForecastMainMakeSaleData::getDayNum134, obj.getDayNum134())
          .eq(Objects.nonNull(obj.getDayNum135()), ApsGoodsForecastMainMakeSaleData::getDayNum135, obj.getDayNum135())
          .eq(Objects.nonNull(obj.getDayNum136()), ApsGoodsForecastMainMakeSaleData::getDayNum136, obj.getDayNum136())
          .eq(Objects.nonNull(obj.getDayNum137()), ApsGoodsForecastMainMakeSaleData::getDayNum137, obj.getDayNum137())
          .eq(Objects.nonNull(obj.getDayNum138()), ApsGoodsForecastMainMakeSaleData::getDayNum138, obj.getDayNum138())
          .eq(Objects.nonNull(obj.getDayNum139()), ApsGoodsForecastMainMakeSaleData::getDayNum139, obj.getDayNum139())
          .eq(Objects.nonNull(obj.getDayNum140()), ApsGoodsForecastMainMakeSaleData::getDayNum140, obj.getDayNum140())
          .eq(Objects.nonNull(obj.getDayNum141()), ApsGoodsForecastMainMakeSaleData::getDayNum141, obj.getDayNum141())
          .eq(Objects.nonNull(obj.getDayNum142()), ApsGoodsForecastMainMakeSaleData::getDayNum142, obj.getDayNum142())
          .eq(Objects.nonNull(obj.getDayNum143()), ApsGoodsForecastMainMakeSaleData::getDayNum143, obj.getDayNum143())
          .eq(Objects.nonNull(obj.getDayNum144()), ApsGoodsForecastMainMakeSaleData::getDayNum144, obj.getDayNum144())
          .eq(Objects.nonNull(obj.getDayNum145()), ApsGoodsForecastMainMakeSaleData::getDayNum145, obj.getDayNum145())
          .eq(Objects.nonNull(obj.getDayNum146()), ApsGoodsForecastMainMakeSaleData::getDayNum146, obj.getDayNum146())
          .eq(Objects.nonNull(obj.getDayNum147()), ApsGoodsForecastMainMakeSaleData::getDayNum147, obj.getDayNum147())
          .eq(Objects.nonNull(obj.getDayNum148()), ApsGoodsForecastMainMakeSaleData::getDayNum148, obj.getDayNum148())
          .eq(Objects.nonNull(obj.getDayNum149()), ApsGoodsForecastMainMakeSaleData::getDayNum149, obj.getDayNum149())
          .eq(Objects.nonNull(obj.getDayNum150()), ApsGoodsForecastMainMakeSaleData::getDayNum150, obj.getDayNum150())
          .eq(Objects.nonNull(obj.getDayNum151()), ApsGoodsForecastMainMakeSaleData::getDayNum151, obj.getDayNum151())
          .eq(Objects.nonNull(obj.getDayNum152()), ApsGoodsForecastMainMakeSaleData::getDayNum152, obj.getDayNum152())
          .eq(Objects.nonNull(obj.getDayNum153()), ApsGoodsForecastMainMakeSaleData::getDayNum153, obj.getDayNum153())
          .eq(Objects.nonNull(obj.getDayNum154()), ApsGoodsForecastMainMakeSaleData::getDayNum154, obj.getDayNum154())
          .eq(Objects.nonNull(obj.getDayNum155()), ApsGoodsForecastMainMakeSaleData::getDayNum155, obj.getDayNum155())
          .eq(Objects.nonNull(obj.getDayNum156()), ApsGoodsForecastMainMakeSaleData::getDayNum156, obj.getDayNum156())
          .eq(Objects.nonNull(obj.getDayNum157()), ApsGoodsForecastMainMakeSaleData::getDayNum157, obj.getDayNum157())
          .eq(Objects.nonNull(obj.getDayNum158()), ApsGoodsForecastMainMakeSaleData::getDayNum158, obj.getDayNum158())
          .eq(Objects.nonNull(obj.getDayNum159()), ApsGoodsForecastMainMakeSaleData::getDayNum159, obj.getDayNum159())
          .eq(Objects.nonNull(obj.getDayNum160()), ApsGoodsForecastMainMakeSaleData::getDayNum160, obj.getDayNum160())
          .eq(Objects.nonNull(obj.getDayNum161()), ApsGoodsForecastMainMakeSaleData::getDayNum161, obj.getDayNum161())
          .eq(Objects.nonNull(obj.getDayNum162()), ApsGoodsForecastMainMakeSaleData::getDayNum162, obj.getDayNum162())
          .eq(Objects.nonNull(obj.getDayNum163()), ApsGoodsForecastMainMakeSaleData::getDayNum163, obj.getDayNum163())
          .eq(Objects.nonNull(obj.getDayNum164()), ApsGoodsForecastMainMakeSaleData::getDayNum164, obj.getDayNum164())
          .eq(Objects.nonNull(obj.getDayNum165()), ApsGoodsForecastMainMakeSaleData::getDayNum165, obj.getDayNum165())
          .eq(Objects.nonNull(obj.getDayNum166()), ApsGoodsForecastMainMakeSaleData::getDayNum166, obj.getDayNum166())
          .eq(Objects.nonNull(obj.getDayNum167()), ApsGoodsForecastMainMakeSaleData::getDayNum167, obj.getDayNum167())
          .eq(Objects.nonNull(obj.getDayNum168()), ApsGoodsForecastMainMakeSaleData::getDayNum168, obj.getDayNum168())
          .eq(Objects.nonNull(obj.getDayNum169()), ApsGoodsForecastMainMakeSaleData::getDayNum169, obj.getDayNum169())
          .eq(Objects.nonNull(obj.getDayNum170()), ApsGoodsForecastMainMakeSaleData::getDayNum170, obj.getDayNum170())
          .eq(Objects.nonNull(obj.getDayNum171()), ApsGoodsForecastMainMakeSaleData::getDayNum171, obj.getDayNum171())
          .eq(Objects.nonNull(obj.getDayNum172()), ApsGoodsForecastMainMakeSaleData::getDayNum172, obj.getDayNum172())
          .eq(Objects.nonNull(obj.getDayNum173()), ApsGoodsForecastMainMakeSaleData::getDayNum173, obj.getDayNum173())
          .eq(Objects.nonNull(obj.getDayNum174()), ApsGoodsForecastMainMakeSaleData::getDayNum174, obj.getDayNum174())
          .eq(Objects.nonNull(obj.getDayNum175()), ApsGoodsForecastMainMakeSaleData::getDayNum175, obj.getDayNum175())
          .eq(Objects.nonNull(obj.getDayNum176()), ApsGoodsForecastMainMakeSaleData::getDayNum176, obj.getDayNum176())
          .eq(Objects.nonNull(obj.getDayNum177()), ApsGoodsForecastMainMakeSaleData::getDayNum177, obj.getDayNum177())
          .eq(Objects.nonNull(obj.getDayNum178()), ApsGoodsForecastMainMakeSaleData::getDayNum178, obj.getDayNum178())
          .eq(Objects.nonNull(obj.getDayNum179()), ApsGoodsForecastMainMakeSaleData::getDayNum179, obj.getDayNum179())
          .eq(Objects.nonNull(obj.getDayNum180()), ApsGoodsForecastMainMakeSaleData::getDayNum180, obj.getDayNum180())
          .eq(Objects.nonNull(obj.getDayNum181()), ApsGoodsForecastMainMakeSaleData::getDayNum181, obj.getDayNum181())
          .eq(Objects.nonNull(obj.getDayNum182()), ApsGoodsForecastMainMakeSaleData::getDayNum182, obj.getDayNum182())
          .eq(Objects.nonNull(obj.getDayNum183()), ApsGoodsForecastMainMakeSaleData::getDayNum183, obj.getDayNum183())
          .eq(Objects.nonNull(obj.getDayNum184()), ApsGoodsForecastMainMakeSaleData::getDayNum184, obj.getDayNum184())
          .eq(Objects.nonNull(obj.getDayNum185()), ApsGoodsForecastMainMakeSaleData::getDayNum185, obj.getDayNum185())
          .eq(Objects.nonNull(obj.getDayNum186()), ApsGoodsForecastMainMakeSaleData::getDayNum186, obj.getDayNum186())
          .eq(Objects.nonNull(obj.getDayNum187()), ApsGoodsForecastMainMakeSaleData::getDayNum187, obj.getDayNum187())
          .eq(Objects.nonNull(obj.getDayNum188()), ApsGoodsForecastMainMakeSaleData::getDayNum188, obj.getDayNum188())
          .eq(Objects.nonNull(obj.getDayNum189()), ApsGoodsForecastMainMakeSaleData::getDayNum189, obj.getDayNum189())
          .eq(Objects.nonNull(obj.getDayNum190()), ApsGoodsForecastMainMakeSaleData::getDayNum190, obj.getDayNum190())
          .eq(Objects.nonNull(obj.getDayNum191()), ApsGoodsForecastMainMakeSaleData::getDayNum191, obj.getDayNum191())
          .eq(Objects.nonNull(obj.getDayNum192()), ApsGoodsForecastMainMakeSaleData::getDayNum192, obj.getDayNum192())
          .eq(Objects.nonNull(obj.getDayNum193()), ApsGoodsForecastMainMakeSaleData::getDayNum193, obj.getDayNum193())
          .eq(Objects.nonNull(obj.getDayNum194()), ApsGoodsForecastMainMakeSaleData::getDayNum194, obj.getDayNum194())
          .eq(Objects.nonNull(obj.getDayNum195()), ApsGoodsForecastMainMakeSaleData::getDayNum195, obj.getDayNum195())
          .eq(Objects.nonNull(obj.getDayNum196()), ApsGoodsForecastMainMakeSaleData::getDayNum196, obj.getDayNum196())
          .eq(Objects.nonNull(obj.getDayNum197()), ApsGoodsForecastMainMakeSaleData::getDayNum197, obj.getDayNum197())
          .eq(Objects.nonNull(obj.getDayNum198()), ApsGoodsForecastMainMakeSaleData::getDayNum198, obj.getDayNum198())
          .eq(Objects.nonNull(obj.getDayNum199()), ApsGoodsForecastMainMakeSaleData::getDayNum199, obj.getDayNum199())
          .eq(Objects.nonNull(obj.getDayNum200()), ApsGoodsForecastMainMakeSaleData::getDayNum200, obj.getDayNum200())
          .eq(Objects.nonNull(obj.getDayNum201()), ApsGoodsForecastMainMakeSaleData::getDayNum201, obj.getDayNum201())
          .eq(Objects.nonNull(obj.getDayNum202()), ApsGoodsForecastMainMakeSaleData::getDayNum202, obj.getDayNum202())
          .eq(Objects.nonNull(obj.getDayNum203()), ApsGoodsForecastMainMakeSaleData::getDayNum203, obj.getDayNum203())
          .eq(Objects.nonNull(obj.getDayNum204()), ApsGoodsForecastMainMakeSaleData::getDayNum204, obj.getDayNum204())
          .eq(Objects.nonNull(obj.getDayNum205()), ApsGoodsForecastMainMakeSaleData::getDayNum205, obj.getDayNum205())
          .eq(Objects.nonNull(obj.getDayNum206()), ApsGoodsForecastMainMakeSaleData::getDayNum206, obj.getDayNum206())
          .eq(Objects.nonNull(obj.getDayNum207()), ApsGoodsForecastMainMakeSaleData::getDayNum207, obj.getDayNum207())
          .eq(Objects.nonNull(obj.getDayNum208()), ApsGoodsForecastMainMakeSaleData::getDayNum208, obj.getDayNum208())
          .eq(Objects.nonNull(obj.getDayNum209()), ApsGoodsForecastMainMakeSaleData::getDayNum209, obj.getDayNum209())
          .eq(Objects.nonNull(obj.getDayNum210()), ApsGoodsForecastMainMakeSaleData::getDayNum210, obj.getDayNum210())
          .eq(Objects.nonNull(obj.getDayNum211()), ApsGoodsForecastMainMakeSaleData::getDayNum211, obj.getDayNum211())
          .eq(Objects.nonNull(obj.getDayNum212()), ApsGoodsForecastMainMakeSaleData::getDayNum212, obj.getDayNum212())
          .eq(Objects.nonNull(obj.getDayNum213()), ApsGoodsForecastMainMakeSaleData::getDayNum213, obj.getDayNum213())
          .eq(Objects.nonNull(obj.getDayNum214()), ApsGoodsForecastMainMakeSaleData::getDayNum214, obj.getDayNum214())
          .eq(Objects.nonNull(obj.getDayNum215()), ApsGoodsForecastMainMakeSaleData::getDayNum215, obj.getDayNum215())
          .eq(Objects.nonNull(obj.getDayNum216()), ApsGoodsForecastMainMakeSaleData::getDayNum216, obj.getDayNum216())
          .eq(Objects.nonNull(obj.getDayNum217()), ApsGoodsForecastMainMakeSaleData::getDayNum217, obj.getDayNum217())
          .eq(Objects.nonNull(obj.getDayNum218()), ApsGoodsForecastMainMakeSaleData::getDayNum218, obj.getDayNum218())
          .eq(Objects.nonNull(obj.getDayNum219()), ApsGoodsForecastMainMakeSaleData::getDayNum219, obj.getDayNum219())
          .eq(Objects.nonNull(obj.getDayNum220()), ApsGoodsForecastMainMakeSaleData::getDayNum220, obj.getDayNum220())
          .eq(Objects.nonNull(obj.getDayNum221()), ApsGoodsForecastMainMakeSaleData::getDayNum221, obj.getDayNum221())
          .eq(Objects.nonNull(obj.getDayNum222()), ApsGoodsForecastMainMakeSaleData::getDayNum222, obj.getDayNum222())
          .eq(Objects.nonNull(obj.getDayNum223()), ApsGoodsForecastMainMakeSaleData::getDayNum223, obj.getDayNum223())
          .eq(Objects.nonNull(obj.getDayNum224()), ApsGoodsForecastMainMakeSaleData::getDayNum224, obj.getDayNum224())
          .eq(Objects.nonNull(obj.getDayNum225()), ApsGoodsForecastMainMakeSaleData::getDayNum225, obj.getDayNum225())
          .eq(Objects.nonNull(obj.getDayNum226()), ApsGoodsForecastMainMakeSaleData::getDayNum226, obj.getDayNum226())
          .eq(Objects.nonNull(obj.getDayNum227()), ApsGoodsForecastMainMakeSaleData::getDayNum227, obj.getDayNum227())
          .eq(Objects.nonNull(obj.getDayNum228()), ApsGoodsForecastMainMakeSaleData::getDayNum228, obj.getDayNum228())
          .eq(Objects.nonNull(obj.getDayNum229()), ApsGoodsForecastMainMakeSaleData::getDayNum229, obj.getDayNum229())
          .eq(Objects.nonNull(obj.getDayNum230()), ApsGoodsForecastMainMakeSaleData::getDayNum230, obj.getDayNum230())
          .eq(Objects.nonNull(obj.getDayNum231()), ApsGoodsForecastMainMakeSaleData::getDayNum231, obj.getDayNum231())
          .eq(Objects.nonNull(obj.getDayNum232()), ApsGoodsForecastMainMakeSaleData::getDayNum232, obj.getDayNum232())
          .eq(Objects.nonNull(obj.getDayNum233()), ApsGoodsForecastMainMakeSaleData::getDayNum233, obj.getDayNum233())
          .eq(Objects.nonNull(obj.getDayNum234()), ApsGoodsForecastMainMakeSaleData::getDayNum234, obj.getDayNum234())
          .eq(Objects.nonNull(obj.getDayNum235()), ApsGoodsForecastMainMakeSaleData::getDayNum235, obj.getDayNum235())
          .eq(Objects.nonNull(obj.getDayNum236()), ApsGoodsForecastMainMakeSaleData::getDayNum236, obj.getDayNum236())
          .eq(Objects.nonNull(obj.getDayNum237()), ApsGoodsForecastMainMakeSaleData::getDayNum237, obj.getDayNum237())
          .eq(Objects.nonNull(obj.getDayNum238()), ApsGoodsForecastMainMakeSaleData::getDayNum238, obj.getDayNum238())
          .eq(Objects.nonNull(obj.getDayNum239()), ApsGoodsForecastMainMakeSaleData::getDayNum239, obj.getDayNum239())
          .eq(Objects.nonNull(obj.getDayNum240()), ApsGoodsForecastMainMakeSaleData::getDayNum240, obj.getDayNum240())
          .eq(Objects.nonNull(obj.getDayNum241()), ApsGoodsForecastMainMakeSaleData::getDayNum241, obj.getDayNum241())
          .eq(Objects.nonNull(obj.getDayNum242()), ApsGoodsForecastMainMakeSaleData::getDayNum242, obj.getDayNum242())
          .eq(Objects.nonNull(obj.getDayNum243()), ApsGoodsForecastMainMakeSaleData::getDayNum243, obj.getDayNum243())
          .eq(Objects.nonNull(obj.getDayNum244()), ApsGoodsForecastMainMakeSaleData::getDayNum244, obj.getDayNum244())
          .eq(Objects.nonNull(obj.getDayNum245()), ApsGoodsForecastMainMakeSaleData::getDayNum245, obj.getDayNum245())
          .eq(Objects.nonNull(obj.getDayNum246()), ApsGoodsForecastMainMakeSaleData::getDayNum246, obj.getDayNum246())
          .eq(Objects.nonNull(obj.getDayNum247()), ApsGoodsForecastMainMakeSaleData::getDayNum247, obj.getDayNum247())
          .eq(Objects.nonNull(obj.getDayNum248()), ApsGoodsForecastMainMakeSaleData::getDayNum248, obj.getDayNum248())
          .eq(Objects.nonNull(obj.getDayNum249()), ApsGoodsForecastMainMakeSaleData::getDayNum249, obj.getDayNum249())
          .eq(Objects.nonNull(obj.getDayNum250()), ApsGoodsForecastMainMakeSaleData::getDayNum250, obj.getDayNum250())
          .eq(Objects.nonNull(obj.getDayNum251()), ApsGoodsForecastMainMakeSaleData::getDayNum251, obj.getDayNum251())
          .eq(Objects.nonNull(obj.getDayNum252()), ApsGoodsForecastMainMakeSaleData::getDayNum252, obj.getDayNum252())
          .eq(Objects.nonNull(obj.getDayNum253()), ApsGoodsForecastMainMakeSaleData::getDayNum253, obj.getDayNum253())
          .eq(Objects.nonNull(obj.getDayNum254()), ApsGoodsForecastMainMakeSaleData::getDayNum254, obj.getDayNum254())
          .eq(Objects.nonNull(obj.getDayNum255()), ApsGoodsForecastMainMakeSaleData::getDayNum255, obj.getDayNum255())
          .eq(Objects.nonNull(obj.getDayNum256()), ApsGoodsForecastMainMakeSaleData::getDayNum256, obj.getDayNum256())
          .eq(Objects.nonNull(obj.getDayNum257()), ApsGoodsForecastMainMakeSaleData::getDayNum257, obj.getDayNum257())
          .eq(Objects.nonNull(obj.getDayNum258()), ApsGoodsForecastMainMakeSaleData::getDayNum258, obj.getDayNum258())
          .eq(Objects.nonNull(obj.getDayNum259()), ApsGoodsForecastMainMakeSaleData::getDayNum259, obj.getDayNum259())
          .eq(Objects.nonNull(obj.getDayNum260()), ApsGoodsForecastMainMakeSaleData::getDayNum260, obj.getDayNum260())
          .eq(Objects.nonNull(obj.getDayNum261()), ApsGoodsForecastMainMakeSaleData::getDayNum261, obj.getDayNum261())
          .eq(Objects.nonNull(obj.getDayNum262()), ApsGoodsForecastMainMakeSaleData::getDayNum262, obj.getDayNum262())
          .eq(Objects.nonNull(obj.getDayNum263()), ApsGoodsForecastMainMakeSaleData::getDayNum263, obj.getDayNum263())
          .eq(Objects.nonNull(obj.getDayNum264()), ApsGoodsForecastMainMakeSaleData::getDayNum264, obj.getDayNum264())
          .eq(Objects.nonNull(obj.getDayNum265()), ApsGoodsForecastMainMakeSaleData::getDayNum265, obj.getDayNum265())
          .eq(Objects.nonNull(obj.getDayNum266()), ApsGoodsForecastMainMakeSaleData::getDayNum266, obj.getDayNum266())
          .eq(Objects.nonNull(obj.getDayNum267()), ApsGoodsForecastMainMakeSaleData::getDayNum267, obj.getDayNum267())
          .eq(Objects.nonNull(obj.getDayNum268()), ApsGoodsForecastMainMakeSaleData::getDayNum268, obj.getDayNum268())
          .eq(Objects.nonNull(obj.getDayNum269()), ApsGoodsForecastMainMakeSaleData::getDayNum269, obj.getDayNum269())
          .eq(Objects.nonNull(obj.getDayNum270()), ApsGoodsForecastMainMakeSaleData::getDayNum270, obj.getDayNum270())
          .eq(Objects.nonNull(obj.getDayNum271()), ApsGoodsForecastMainMakeSaleData::getDayNum271, obj.getDayNum271())
          .eq(Objects.nonNull(obj.getDayNum272()), ApsGoodsForecastMainMakeSaleData::getDayNum272, obj.getDayNum272())
          .eq(Objects.nonNull(obj.getDayNum273()), ApsGoodsForecastMainMakeSaleData::getDayNum273, obj.getDayNum273())
          .eq(Objects.nonNull(obj.getDayNum274()), ApsGoodsForecastMainMakeSaleData::getDayNum274, obj.getDayNum274())
          .eq(Objects.nonNull(obj.getDayNum275()), ApsGoodsForecastMainMakeSaleData::getDayNum275, obj.getDayNum275())
          .eq(Objects.nonNull(obj.getDayNum276()), ApsGoodsForecastMainMakeSaleData::getDayNum276, obj.getDayNum276())
          .eq(Objects.nonNull(obj.getDayNum277()), ApsGoodsForecastMainMakeSaleData::getDayNum277, obj.getDayNum277())
          .eq(Objects.nonNull(obj.getDayNum278()), ApsGoodsForecastMainMakeSaleData::getDayNum278, obj.getDayNum278())
          .eq(Objects.nonNull(obj.getDayNum279()), ApsGoodsForecastMainMakeSaleData::getDayNum279, obj.getDayNum279())
          .eq(Objects.nonNull(obj.getDayNum280()), ApsGoodsForecastMainMakeSaleData::getDayNum280, obj.getDayNum280())
          .eq(Objects.nonNull(obj.getDayNum281()), ApsGoodsForecastMainMakeSaleData::getDayNum281, obj.getDayNum281())
          .eq(Objects.nonNull(obj.getDayNum282()), ApsGoodsForecastMainMakeSaleData::getDayNum282, obj.getDayNum282())
          .eq(Objects.nonNull(obj.getDayNum283()), ApsGoodsForecastMainMakeSaleData::getDayNum283, obj.getDayNum283())
          .eq(Objects.nonNull(obj.getDayNum284()), ApsGoodsForecastMainMakeSaleData::getDayNum284, obj.getDayNum284())
          .eq(Objects.nonNull(obj.getDayNum285()), ApsGoodsForecastMainMakeSaleData::getDayNum285, obj.getDayNum285())
          .eq(Objects.nonNull(obj.getDayNum286()), ApsGoodsForecastMainMakeSaleData::getDayNum286, obj.getDayNum286())
          .eq(Objects.nonNull(obj.getDayNum287()), ApsGoodsForecastMainMakeSaleData::getDayNum287, obj.getDayNum287())
          .eq(Objects.nonNull(obj.getDayNum288()), ApsGoodsForecastMainMakeSaleData::getDayNum288, obj.getDayNum288())
          .eq(Objects.nonNull(obj.getDayNum289()), ApsGoodsForecastMainMakeSaleData::getDayNum289, obj.getDayNum289())
          .eq(Objects.nonNull(obj.getDayNum290()), ApsGoodsForecastMainMakeSaleData::getDayNum290, obj.getDayNum290())
          .eq(Objects.nonNull(obj.getDayNum291()), ApsGoodsForecastMainMakeSaleData::getDayNum291, obj.getDayNum291())
          .eq(Objects.nonNull(obj.getDayNum292()), ApsGoodsForecastMainMakeSaleData::getDayNum292, obj.getDayNum292())
          .eq(Objects.nonNull(obj.getDayNum293()), ApsGoodsForecastMainMakeSaleData::getDayNum293, obj.getDayNum293())
          .eq(Objects.nonNull(obj.getDayNum294()), ApsGoodsForecastMainMakeSaleData::getDayNum294, obj.getDayNum294())
          .eq(Objects.nonNull(obj.getDayNum295()), ApsGoodsForecastMainMakeSaleData::getDayNum295, obj.getDayNum295())
          .eq(Objects.nonNull(obj.getDayNum296()), ApsGoodsForecastMainMakeSaleData::getDayNum296, obj.getDayNum296())
          .eq(Objects.nonNull(obj.getDayNum297()), ApsGoodsForecastMainMakeSaleData::getDayNum297, obj.getDayNum297())
          .eq(Objects.nonNull(obj.getDayNum298()), ApsGoodsForecastMainMakeSaleData::getDayNum298, obj.getDayNum298())
          .eq(Objects.nonNull(obj.getDayNum299()), ApsGoodsForecastMainMakeSaleData::getDayNum299, obj.getDayNum299())
          .eq(Objects.nonNull(obj.getDayNum300()), ApsGoodsForecastMainMakeSaleData::getDayNum300, obj.getDayNum300())
          .eq(Objects.nonNull(obj.getDayNum301()), ApsGoodsForecastMainMakeSaleData::getDayNum301, obj.getDayNum301())
          .eq(Objects.nonNull(obj.getDayNum302()), ApsGoodsForecastMainMakeSaleData::getDayNum302, obj.getDayNum302())
          .eq(Objects.nonNull(obj.getDayNum303()), ApsGoodsForecastMainMakeSaleData::getDayNum303, obj.getDayNum303())
          .eq(Objects.nonNull(obj.getDayNum304()), ApsGoodsForecastMainMakeSaleData::getDayNum304, obj.getDayNum304())
          .eq(Objects.nonNull(obj.getDayNum305()), ApsGoodsForecastMainMakeSaleData::getDayNum305, obj.getDayNum305())
          .eq(Objects.nonNull(obj.getDayNum306()), ApsGoodsForecastMainMakeSaleData::getDayNum306, obj.getDayNum306())
          .eq(Objects.nonNull(obj.getDayNum307()), ApsGoodsForecastMainMakeSaleData::getDayNum307, obj.getDayNum307())
          .eq(Objects.nonNull(obj.getDayNum308()), ApsGoodsForecastMainMakeSaleData::getDayNum308, obj.getDayNum308())
          .eq(Objects.nonNull(obj.getDayNum309()), ApsGoodsForecastMainMakeSaleData::getDayNum309, obj.getDayNum309())
          .eq(Objects.nonNull(obj.getDayNum310()), ApsGoodsForecastMainMakeSaleData::getDayNum310, obj.getDayNum310())
          .eq(Objects.nonNull(obj.getDayNum311()), ApsGoodsForecastMainMakeSaleData::getDayNum311, obj.getDayNum311())
          .eq(Objects.nonNull(obj.getDayNum312()), ApsGoodsForecastMainMakeSaleData::getDayNum312, obj.getDayNum312())
          .eq(Objects.nonNull(obj.getDayNum313()), ApsGoodsForecastMainMakeSaleData::getDayNum313, obj.getDayNum313())
          .eq(Objects.nonNull(obj.getDayNum314()), ApsGoodsForecastMainMakeSaleData::getDayNum314, obj.getDayNum314())
          .eq(Objects.nonNull(obj.getDayNum315()), ApsGoodsForecastMainMakeSaleData::getDayNum315, obj.getDayNum315())
          .eq(Objects.nonNull(obj.getDayNum316()), ApsGoodsForecastMainMakeSaleData::getDayNum316, obj.getDayNum316())
          .eq(Objects.nonNull(obj.getDayNum317()), ApsGoodsForecastMainMakeSaleData::getDayNum317, obj.getDayNum317())
          .eq(Objects.nonNull(obj.getDayNum318()), ApsGoodsForecastMainMakeSaleData::getDayNum318, obj.getDayNum318())
          .eq(Objects.nonNull(obj.getDayNum319()), ApsGoodsForecastMainMakeSaleData::getDayNum319, obj.getDayNum319())
          .eq(Objects.nonNull(obj.getDayNum320()), ApsGoodsForecastMainMakeSaleData::getDayNum320, obj.getDayNum320())
          .eq(Objects.nonNull(obj.getDayNum321()), ApsGoodsForecastMainMakeSaleData::getDayNum321, obj.getDayNum321())
          .eq(Objects.nonNull(obj.getDayNum322()), ApsGoodsForecastMainMakeSaleData::getDayNum322, obj.getDayNum322())
          .eq(Objects.nonNull(obj.getDayNum323()), ApsGoodsForecastMainMakeSaleData::getDayNum323, obj.getDayNum323())
          .eq(Objects.nonNull(obj.getDayNum324()), ApsGoodsForecastMainMakeSaleData::getDayNum324, obj.getDayNum324())
          .eq(Objects.nonNull(obj.getDayNum325()), ApsGoodsForecastMainMakeSaleData::getDayNum325, obj.getDayNum325())
          .eq(Objects.nonNull(obj.getDayNum326()), ApsGoodsForecastMainMakeSaleData::getDayNum326, obj.getDayNum326())
          .eq(Objects.nonNull(obj.getDayNum327()), ApsGoodsForecastMainMakeSaleData::getDayNum327, obj.getDayNum327())
          .eq(Objects.nonNull(obj.getDayNum328()), ApsGoodsForecastMainMakeSaleData::getDayNum328, obj.getDayNum328())
          .eq(Objects.nonNull(obj.getDayNum329()), ApsGoodsForecastMainMakeSaleData::getDayNum329, obj.getDayNum329())
          .eq(Objects.nonNull(obj.getDayNum330()), ApsGoodsForecastMainMakeSaleData::getDayNum330, obj.getDayNum330())
          .eq(Objects.nonNull(obj.getDayNum331()), ApsGoodsForecastMainMakeSaleData::getDayNum331, obj.getDayNum331())
          .eq(Objects.nonNull(obj.getDayNum332()), ApsGoodsForecastMainMakeSaleData::getDayNum332, obj.getDayNum332())
          .eq(Objects.nonNull(obj.getDayNum333()), ApsGoodsForecastMainMakeSaleData::getDayNum333, obj.getDayNum333())
          .eq(Objects.nonNull(obj.getDayNum334()), ApsGoodsForecastMainMakeSaleData::getDayNum334, obj.getDayNum334())
          .eq(Objects.nonNull(obj.getDayNum335()), ApsGoodsForecastMainMakeSaleData::getDayNum335, obj.getDayNum335())
          .eq(Objects.nonNull(obj.getDayNum336()), ApsGoodsForecastMainMakeSaleData::getDayNum336, obj.getDayNum336())
          .eq(Objects.nonNull(obj.getDayNum337()), ApsGoodsForecastMainMakeSaleData::getDayNum337, obj.getDayNum337())
          .eq(Objects.nonNull(obj.getDayNum338()), ApsGoodsForecastMainMakeSaleData::getDayNum338, obj.getDayNum338())
          .eq(Objects.nonNull(obj.getDayNum339()), ApsGoodsForecastMainMakeSaleData::getDayNum339, obj.getDayNum339())
          .eq(Objects.nonNull(obj.getDayNum340()), ApsGoodsForecastMainMakeSaleData::getDayNum340, obj.getDayNum340())
          .eq(Objects.nonNull(obj.getDayNum341()), ApsGoodsForecastMainMakeSaleData::getDayNum341, obj.getDayNum341())
          .eq(Objects.nonNull(obj.getDayNum342()), ApsGoodsForecastMainMakeSaleData::getDayNum342, obj.getDayNum342())
          .eq(Objects.nonNull(obj.getDayNum343()), ApsGoodsForecastMainMakeSaleData::getDayNum343, obj.getDayNum343())
          .eq(Objects.nonNull(obj.getDayNum344()), ApsGoodsForecastMainMakeSaleData::getDayNum344, obj.getDayNum344())
          .eq(Objects.nonNull(obj.getDayNum345()), ApsGoodsForecastMainMakeSaleData::getDayNum345, obj.getDayNum345())
          .eq(Objects.nonNull(obj.getDayNum346()), ApsGoodsForecastMainMakeSaleData::getDayNum346, obj.getDayNum346())
          .eq(Objects.nonNull(obj.getDayNum347()), ApsGoodsForecastMainMakeSaleData::getDayNum347, obj.getDayNum347())
          .eq(Objects.nonNull(obj.getDayNum348()), ApsGoodsForecastMainMakeSaleData::getDayNum348, obj.getDayNum348())
          .eq(Objects.nonNull(obj.getDayNum349()), ApsGoodsForecastMainMakeSaleData::getDayNum349, obj.getDayNum349())
          .eq(Objects.nonNull(obj.getDayNum350()), ApsGoodsForecastMainMakeSaleData::getDayNum350, obj.getDayNum350())
          .eq(Objects.nonNull(obj.getDayNum351()), ApsGoodsForecastMainMakeSaleData::getDayNum351, obj.getDayNum351())
          .eq(Objects.nonNull(obj.getDayNum352()), ApsGoodsForecastMainMakeSaleData::getDayNum352, obj.getDayNum352())
          .eq(Objects.nonNull(obj.getDayNum353()), ApsGoodsForecastMainMakeSaleData::getDayNum353, obj.getDayNum353())
          .eq(Objects.nonNull(obj.getDayNum354()), ApsGoodsForecastMainMakeSaleData::getDayNum354, obj.getDayNum354())
          .eq(Objects.nonNull(obj.getDayNum355()), ApsGoodsForecastMainMakeSaleData::getDayNum355, obj.getDayNum355())
          .eq(Objects.nonNull(obj.getDayNum356()), ApsGoodsForecastMainMakeSaleData::getDayNum356, obj.getDayNum356())
          .eq(Objects.nonNull(obj.getDayNum357()), ApsGoodsForecastMainMakeSaleData::getDayNum357, obj.getDayNum357())
          .eq(Objects.nonNull(obj.getDayNum358()), ApsGoodsForecastMainMakeSaleData::getDayNum358, obj.getDayNum358())
          .eq(Objects.nonNull(obj.getDayNum359()), ApsGoodsForecastMainMakeSaleData::getDayNum359, obj.getDayNum359())
          .eq(Objects.nonNull(obj.getDayNum360()), ApsGoodsForecastMainMakeSaleData::getDayNum360, obj.getDayNum360())
          .eq(Objects.nonNull(obj.getDayNum361()), ApsGoodsForecastMainMakeSaleData::getDayNum361, obj.getDayNum361())
          .eq(Objects.nonNull(obj.getDayNum362()), ApsGoodsForecastMainMakeSaleData::getDayNum362, obj.getDayNum362())
          .eq(Objects.nonNull(obj.getDayNum363()), ApsGoodsForecastMainMakeSaleData::getDayNum363, obj.getDayNum363())
          .eq(Objects.nonNull(obj.getDayNum364()), ApsGoodsForecastMainMakeSaleData::getDayNum364, obj.getDayNum364())
          .eq(Objects.nonNull(obj.getDayNum365()), ApsGoodsForecastMainMakeSaleData::getDayNum365, obj.getDayNum365())
          .eq(Objects.nonNull(obj.getDayNum366()), ApsGoodsForecastMainMakeSaleData::getDayNum366, obj.getDayNum366())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMainMakeSaleData::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMainMakeSaleData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMainMakeSaleData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMainMakeSaleDataService#queryPageList");

  }


}

