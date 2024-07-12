package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMakeProjectDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeProjectData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeProjectDataService;
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
 * (ApsGoodsForecastMakeProjectData)表服务实现类
 *
 * @author peanut
 * @since 2024-05-10 13:58:08
 */
@Service("apsGoodsForecastMakeProjectDataService")
@Transactional
public class ApsGoodsForecastMakeProjectDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMakeProjectDataMapper, ApsGoodsForecastMakeProjectData> implements
    ApsGoodsForecastMakeProjectDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMakeProjectDataQueryListRes queryList(ApsGoodsForecastMakeProjectDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeProjectData> list = this.list(q);

    List<ApsGoodsForecastMakeProjectDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMakeProjectDataDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);

    ((ApsGoodsForecastMakeProjectDataServiceImpl) AopContext.currentProxy()).setName(dataList);
    return new ApsGoodsForecastMakeProjectDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeProjectDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMakeProjectData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMakeProjectData> list = this.page(page, q);
      IPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMakeProjectDataServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMakeProjectDataDto> apsGoodsForecastMakeProjectDataDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMakeProjectDataDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> getWrapper(ApsGoodsForecastMakeProjectDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMakeProjectData::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMakeMonthId()), ApsGoodsForecastMakeProjectData::getMakeMonthId, obj.getMakeMonthId())
          .eq(StringUtils.isNoneBlank(obj.getProjectConfigCode()), ApsGoodsForecastMakeProjectData::getProjectConfigCode, obj.getProjectConfigCode())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMakeProjectData::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getDayNum1()), ApsGoodsForecastMakeProjectData::getDayNum1, obj.getDayNum1())
          .eq(Objects.nonNull(obj.getDayNum2()), ApsGoodsForecastMakeProjectData::getDayNum2, obj.getDayNum2())
          .eq(Objects.nonNull(obj.getDayNum3()), ApsGoodsForecastMakeProjectData::getDayNum3, obj.getDayNum3())
          .eq(Objects.nonNull(obj.getDayNum4()), ApsGoodsForecastMakeProjectData::getDayNum4, obj.getDayNum4())
          .eq(Objects.nonNull(obj.getDayNum5()), ApsGoodsForecastMakeProjectData::getDayNum5, obj.getDayNum5())
          .eq(Objects.nonNull(obj.getDayNum6()), ApsGoodsForecastMakeProjectData::getDayNum6, obj.getDayNum6())
          .eq(Objects.nonNull(obj.getDayNum7()), ApsGoodsForecastMakeProjectData::getDayNum7, obj.getDayNum7())
          .eq(Objects.nonNull(obj.getDayNum8()), ApsGoodsForecastMakeProjectData::getDayNum8, obj.getDayNum8())
          .eq(Objects.nonNull(obj.getDayNum9()), ApsGoodsForecastMakeProjectData::getDayNum9, obj.getDayNum9())
          .eq(Objects.nonNull(obj.getDayNum10()), ApsGoodsForecastMakeProjectData::getDayNum10, obj.getDayNum10())
          .eq(Objects.nonNull(obj.getDayNum11()), ApsGoodsForecastMakeProjectData::getDayNum11, obj.getDayNum11())
          .eq(Objects.nonNull(obj.getDayNum12()), ApsGoodsForecastMakeProjectData::getDayNum12, obj.getDayNum12())
          .eq(Objects.nonNull(obj.getDayNum13()), ApsGoodsForecastMakeProjectData::getDayNum13, obj.getDayNum13())
          .eq(Objects.nonNull(obj.getDayNum14()), ApsGoodsForecastMakeProjectData::getDayNum14, obj.getDayNum14())
          .eq(Objects.nonNull(obj.getDayNum15()), ApsGoodsForecastMakeProjectData::getDayNum15, obj.getDayNum15())
          .eq(Objects.nonNull(obj.getDayNum16()), ApsGoodsForecastMakeProjectData::getDayNum16, obj.getDayNum16())
          .eq(Objects.nonNull(obj.getDayNum17()), ApsGoodsForecastMakeProjectData::getDayNum17, obj.getDayNum17())
          .eq(Objects.nonNull(obj.getDayNum18()), ApsGoodsForecastMakeProjectData::getDayNum18, obj.getDayNum18())
          .eq(Objects.nonNull(obj.getDayNum19()), ApsGoodsForecastMakeProjectData::getDayNum19, obj.getDayNum19())
          .eq(Objects.nonNull(obj.getDayNum20()), ApsGoodsForecastMakeProjectData::getDayNum20, obj.getDayNum20())
          .eq(Objects.nonNull(obj.getDayNum21()), ApsGoodsForecastMakeProjectData::getDayNum21, obj.getDayNum21())
          .eq(Objects.nonNull(obj.getDayNum22()), ApsGoodsForecastMakeProjectData::getDayNum22, obj.getDayNum22())
          .eq(Objects.nonNull(obj.getDayNum23()), ApsGoodsForecastMakeProjectData::getDayNum23, obj.getDayNum23())
          .eq(Objects.nonNull(obj.getDayNum24()), ApsGoodsForecastMakeProjectData::getDayNum24, obj.getDayNum24())
          .eq(Objects.nonNull(obj.getDayNum25()), ApsGoodsForecastMakeProjectData::getDayNum25, obj.getDayNum25())
          .eq(Objects.nonNull(obj.getDayNum26()), ApsGoodsForecastMakeProjectData::getDayNum26, obj.getDayNum26())
          .eq(Objects.nonNull(obj.getDayNum27()), ApsGoodsForecastMakeProjectData::getDayNum27, obj.getDayNum27())
          .eq(Objects.nonNull(obj.getDayNum28()), ApsGoodsForecastMakeProjectData::getDayNum28, obj.getDayNum28())
          .eq(Objects.nonNull(obj.getDayNum29()), ApsGoodsForecastMakeProjectData::getDayNum29, obj.getDayNum29())
          .eq(Objects.nonNull(obj.getDayNum30()), ApsGoodsForecastMakeProjectData::getDayNum30, obj.getDayNum30())
          .eq(Objects.nonNull(obj.getDayNum31()), ApsGoodsForecastMakeProjectData::getDayNum31, obj.getDayNum31())
          .eq(Objects.nonNull(obj.getDayNum32()), ApsGoodsForecastMakeProjectData::getDayNum32, obj.getDayNum32())
          .eq(Objects.nonNull(obj.getDayNum33()), ApsGoodsForecastMakeProjectData::getDayNum33, obj.getDayNum33())
          .eq(Objects.nonNull(obj.getDayNum34()), ApsGoodsForecastMakeProjectData::getDayNum34, obj.getDayNum34())
          .eq(Objects.nonNull(obj.getDayNum35()), ApsGoodsForecastMakeProjectData::getDayNum35, obj.getDayNum35())
          .eq(Objects.nonNull(obj.getDayNum36()), ApsGoodsForecastMakeProjectData::getDayNum36, obj.getDayNum36())
          .eq(Objects.nonNull(obj.getDayNum37()), ApsGoodsForecastMakeProjectData::getDayNum37, obj.getDayNum37())
          .eq(Objects.nonNull(obj.getDayNum38()), ApsGoodsForecastMakeProjectData::getDayNum38, obj.getDayNum38())
          .eq(Objects.nonNull(obj.getDayNum39()), ApsGoodsForecastMakeProjectData::getDayNum39, obj.getDayNum39())
          .eq(Objects.nonNull(obj.getDayNum40()), ApsGoodsForecastMakeProjectData::getDayNum40, obj.getDayNum40())
          .eq(Objects.nonNull(obj.getDayNum41()), ApsGoodsForecastMakeProjectData::getDayNum41, obj.getDayNum41())
          .eq(Objects.nonNull(obj.getDayNum42()), ApsGoodsForecastMakeProjectData::getDayNum42, obj.getDayNum42())
          .eq(Objects.nonNull(obj.getDayNum43()), ApsGoodsForecastMakeProjectData::getDayNum43, obj.getDayNum43())
          .eq(Objects.nonNull(obj.getDayNum44()), ApsGoodsForecastMakeProjectData::getDayNum44, obj.getDayNum44())
          .eq(Objects.nonNull(obj.getDayNum45()), ApsGoodsForecastMakeProjectData::getDayNum45, obj.getDayNum45())
          .eq(Objects.nonNull(obj.getDayNum46()), ApsGoodsForecastMakeProjectData::getDayNum46, obj.getDayNum46())
          .eq(Objects.nonNull(obj.getDayNum47()), ApsGoodsForecastMakeProjectData::getDayNum47, obj.getDayNum47())
          .eq(Objects.nonNull(obj.getDayNum48()), ApsGoodsForecastMakeProjectData::getDayNum48, obj.getDayNum48())
          .eq(Objects.nonNull(obj.getDayNum49()), ApsGoodsForecastMakeProjectData::getDayNum49, obj.getDayNum49())
          .eq(Objects.nonNull(obj.getDayNum50()), ApsGoodsForecastMakeProjectData::getDayNum50, obj.getDayNum50())
          .eq(Objects.nonNull(obj.getDayNum51()), ApsGoodsForecastMakeProjectData::getDayNum51, obj.getDayNum51())
          .eq(Objects.nonNull(obj.getDayNum52()), ApsGoodsForecastMakeProjectData::getDayNum52, obj.getDayNum52())
          .eq(Objects.nonNull(obj.getDayNum53()), ApsGoodsForecastMakeProjectData::getDayNum53, obj.getDayNum53())
          .eq(Objects.nonNull(obj.getDayNum54()), ApsGoodsForecastMakeProjectData::getDayNum54, obj.getDayNum54())
          .eq(Objects.nonNull(obj.getDayNum55()), ApsGoodsForecastMakeProjectData::getDayNum55, obj.getDayNum55())
          .eq(Objects.nonNull(obj.getDayNum56()), ApsGoodsForecastMakeProjectData::getDayNum56, obj.getDayNum56())
          .eq(Objects.nonNull(obj.getDayNum57()), ApsGoodsForecastMakeProjectData::getDayNum57, obj.getDayNum57())
          .eq(Objects.nonNull(obj.getDayNum58()), ApsGoodsForecastMakeProjectData::getDayNum58, obj.getDayNum58())
          .eq(Objects.nonNull(obj.getDayNum59()), ApsGoodsForecastMakeProjectData::getDayNum59, obj.getDayNum59())
          .eq(Objects.nonNull(obj.getDayNum60()), ApsGoodsForecastMakeProjectData::getDayNum60, obj.getDayNum60())
          .eq(Objects.nonNull(obj.getDayNum61()), ApsGoodsForecastMakeProjectData::getDayNum61, obj.getDayNum61())
          .eq(Objects.nonNull(obj.getDayNum62()), ApsGoodsForecastMakeProjectData::getDayNum62, obj.getDayNum62())
          .eq(Objects.nonNull(obj.getDayNum63()), ApsGoodsForecastMakeProjectData::getDayNum63, obj.getDayNum63())
          .eq(Objects.nonNull(obj.getDayNum64()), ApsGoodsForecastMakeProjectData::getDayNum64, obj.getDayNum64())
          .eq(Objects.nonNull(obj.getDayNum65()), ApsGoodsForecastMakeProjectData::getDayNum65, obj.getDayNum65())
          .eq(Objects.nonNull(obj.getDayNum66()), ApsGoodsForecastMakeProjectData::getDayNum66, obj.getDayNum66())
          .eq(Objects.nonNull(obj.getDayNum67()), ApsGoodsForecastMakeProjectData::getDayNum67, obj.getDayNum67())
          .eq(Objects.nonNull(obj.getDayNum68()), ApsGoodsForecastMakeProjectData::getDayNum68, obj.getDayNum68())
          .eq(Objects.nonNull(obj.getDayNum69()), ApsGoodsForecastMakeProjectData::getDayNum69, obj.getDayNum69())
          .eq(Objects.nonNull(obj.getDayNum70()), ApsGoodsForecastMakeProjectData::getDayNum70, obj.getDayNum70())
          .eq(Objects.nonNull(obj.getDayNum71()), ApsGoodsForecastMakeProjectData::getDayNum71, obj.getDayNum71())
          .eq(Objects.nonNull(obj.getDayNum72()), ApsGoodsForecastMakeProjectData::getDayNum72, obj.getDayNum72())
          .eq(Objects.nonNull(obj.getDayNum73()), ApsGoodsForecastMakeProjectData::getDayNum73, obj.getDayNum73())
          .eq(Objects.nonNull(obj.getDayNum74()), ApsGoodsForecastMakeProjectData::getDayNum74, obj.getDayNum74())
          .eq(Objects.nonNull(obj.getDayNum75()), ApsGoodsForecastMakeProjectData::getDayNum75, obj.getDayNum75())
          .eq(Objects.nonNull(obj.getDayNum76()), ApsGoodsForecastMakeProjectData::getDayNum76, obj.getDayNum76())
          .eq(Objects.nonNull(obj.getDayNum77()), ApsGoodsForecastMakeProjectData::getDayNum77, obj.getDayNum77())
          .eq(Objects.nonNull(obj.getDayNum78()), ApsGoodsForecastMakeProjectData::getDayNum78, obj.getDayNum78())
          .eq(Objects.nonNull(obj.getDayNum79()), ApsGoodsForecastMakeProjectData::getDayNum79, obj.getDayNum79())
          .eq(Objects.nonNull(obj.getDayNum80()), ApsGoodsForecastMakeProjectData::getDayNum80, obj.getDayNum80())
          .eq(Objects.nonNull(obj.getDayNum81()), ApsGoodsForecastMakeProjectData::getDayNum81, obj.getDayNum81())
          .eq(Objects.nonNull(obj.getDayNum82()), ApsGoodsForecastMakeProjectData::getDayNum82, obj.getDayNum82())
          .eq(Objects.nonNull(obj.getDayNum83()), ApsGoodsForecastMakeProjectData::getDayNum83, obj.getDayNum83())
          .eq(Objects.nonNull(obj.getDayNum84()), ApsGoodsForecastMakeProjectData::getDayNum84, obj.getDayNum84())
          .eq(Objects.nonNull(obj.getDayNum85()), ApsGoodsForecastMakeProjectData::getDayNum85, obj.getDayNum85())
          .eq(Objects.nonNull(obj.getDayNum86()), ApsGoodsForecastMakeProjectData::getDayNum86, obj.getDayNum86())
          .eq(Objects.nonNull(obj.getDayNum87()), ApsGoodsForecastMakeProjectData::getDayNum87, obj.getDayNum87())
          .eq(Objects.nonNull(obj.getDayNum88()), ApsGoodsForecastMakeProjectData::getDayNum88, obj.getDayNum88())
          .eq(Objects.nonNull(obj.getDayNum89()), ApsGoodsForecastMakeProjectData::getDayNum89, obj.getDayNum89())
          .eq(Objects.nonNull(obj.getDayNum90()), ApsGoodsForecastMakeProjectData::getDayNum90, obj.getDayNum90())
          .eq(Objects.nonNull(obj.getDayNum91()), ApsGoodsForecastMakeProjectData::getDayNum91, obj.getDayNum91())
          .eq(Objects.nonNull(obj.getDayNum92()), ApsGoodsForecastMakeProjectData::getDayNum92, obj.getDayNum92())
          .eq(Objects.nonNull(obj.getDayNum93()), ApsGoodsForecastMakeProjectData::getDayNum93, obj.getDayNum93())
          .eq(Objects.nonNull(obj.getDayNum94()), ApsGoodsForecastMakeProjectData::getDayNum94, obj.getDayNum94())
          .eq(Objects.nonNull(obj.getDayNum95()), ApsGoodsForecastMakeProjectData::getDayNum95, obj.getDayNum95())
          .eq(Objects.nonNull(obj.getDayNum96()), ApsGoodsForecastMakeProjectData::getDayNum96, obj.getDayNum96())
          .eq(Objects.nonNull(obj.getDayNum97()), ApsGoodsForecastMakeProjectData::getDayNum97, obj.getDayNum97())
          .eq(Objects.nonNull(obj.getDayNum98()), ApsGoodsForecastMakeProjectData::getDayNum98, obj.getDayNum98())
          .eq(Objects.nonNull(obj.getDayNum99()), ApsGoodsForecastMakeProjectData::getDayNum99, obj.getDayNum99())
          .eq(Objects.nonNull(obj.getDayNum100()), ApsGoodsForecastMakeProjectData::getDayNum100, obj.getDayNum100())
          .eq(Objects.nonNull(obj.getDayNum101()), ApsGoodsForecastMakeProjectData::getDayNum101, obj.getDayNum101())
          .eq(Objects.nonNull(obj.getDayNum102()), ApsGoodsForecastMakeProjectData::getDayNum102, obj.getDayNum102())
          .eq(Objects.nonNull(obj.getDayNum103()), ApsGoodsForecastMakeProjectData::getDayNum103, obj.getDayNum103())
          .eq(Objects.nonNull(obj.getDayNum104()), ApsGoodsForecastMakeProjectData::getDayNum104, obj.getDayNum104())
          .eq(Objects.nonNull(obj.getDayNum105()), ApsGoodsForecastMakeProjectData::getDayNum105, obj.getDayNum105())
          .eq(Objects.nonNull(obj.getDayNum106()), ApsGoodsForecastMakeProjectData::getDayNum106, obj.getDayNum106())
          .eq(Objects.nonNull(obj.getDayNum107()), ApsGoodsForecastMakeProjectData::getDayNum107, obj.getDayNum107())
          .eq(Objects.nonNull(obj.getDayNum108()), ApsGoodsForecastMakeProjectData::getDayNum108, obj.getDayNum108())
          .eq(Objects.nonNull(obj.getDayNum109()), ApsGoodsForecastMakeProjectData::getDayNum109, obj.getDayNum109())
          .eq(Objects.nonNull(obj.getDayNum110()), ApsGoodsForecastMakeProjectData::getDayNum110, obj.getDayNum110())
          .eq(Objects.nonNull(obj.getDayNum111()), ApsGoodsForecastMakeProjectData::getDayNum111, obj.getDayNum111())
          .eq(Objects.nonNull(obj.getDayNum112()), ApsGoodsForecastMakeProjectData::getDayNum112, obj.getDayNum112())
          .eq(Objects.nonNull(obj.getDayNum113()), ApsGoodsForecastMakeProjectData::getDayNum113, obj.getDayNum113())
          .eq(Objects.nonNull(obj.getDayNum114()), ApsGoodsForecastMakeProjectData::getDayNum114, obj.getDayNum114())
          .eq(Objects.nonNull(obj.getDayNum115()), ApsGoodsForecastMakeProjectData::getDayNum115, obj.getDayNum115())
          .eq(Objects.nonNull(obj.getDayNum116()), ApsGoodsForecastMakeProjectData::getDayNum116, obj.getDayNum116())
          .eq(Objects.nonNull(obj.getDayNum117()), ApsGoodsForecastMakeProjectData::getDayNum117, obj.getDayNum117())
          .eq(Objects.nonNull(obj.getDayNum118()), ApsGoodsForecastMakeProjectData::getDayNum118, obj.getDayNum118())
          .eq(Objects.nonNull(obj.getDayNum119()), ApsGoodsForecastMakeProjectData::getDayNum119, obj.getDayNum119())
          .eq(Objects.nonNull(obj.getDayNum120()), ApsGoodsForecastMakeProjectData::getDayNum120, obj.getDayNum120())
          .eq(Objects.nonNull(obj.getDayNum121()), ApsGoodsForecastMakeProjectData::getDayNum121, obj.getDayNum121())
          .eq(Objects.nonNull(obj.getDayNum122()), ApsGoodsForecastMakeProjectData::getDayNum122, obj.getDayNum122())
          .eq(Objects.nonNull(obj.getDayNum123()), ApsGoodsForecastMakeProjectData::getDayNum123, obj.getDayNum123())
          .eq(Objects.nonNull(obj.getDayNum124()), ApsGoodsForecastMakeProjectData::getDayNum124, obj.getDayNum124())
          .eq(Objects.nonNull(obj.getDayNum125()), ApsGoodsForecastMakeProjectData::getDayNum125, obj.getDayNum125())
          .eq(Objects.nonNull(obj.getDayNum126()), ApsGoodsForecastMakeProjectData::getDayNum126, obj.getDayNum126())
          .eq(Objects.nonNull(obj.getDayNum127()), ApsGoodsForecastMakeProjectData::getDayNum127, obj.getDayNum127())
          .eq(Objects.nonNull(obj.getDayNum128()), ApsGoodsForecastMakeProjectData::getDayNum128, obj.getDayNum128())
          .eq(Objects.nonNull(obj.getDayNum129()), ApsGoodsForecastMakeProjectData::getDayNum129, obj.getDayNum129())
          .eq(Objects.nonNull(obj.getDayNum130()), ApsGoodsForecastMakeProjectData::getDayNum130, obj.getDayNum130())
          .eq(Objects.nonNull(obj.getDayNum131()), ApsGoodsForecastMakeProjectData::getDayNum131, obj.getDayNum131())
          .eq(Objects.nonNull(obj.getDayNum132()), ApsGoodsForecastMakeProjectData::getDayNum132, obj.getDayNum132())
          .eq(Objects.nonNull(obj.getDayNum133()), ApsGoodsForecastMakeProjectData::getDayNum133, obj.getDayNum133())
          .eq(Objects.nonNull(obj.getDayNum134()), ApsGoodsForecastMakeProjectData::getDayNum134, obj.getDayNum134())
          .eq(Objects.nonNull(obj.getDayNum135()), ApsGoodsForecastMakeProjectData::getDayNum135, obj.getDayNum135())
          .eq(Objects.nonNull(obj.getDayNum136()), ApsGoodsForecastMakeProjectData::getDayNum136, obj.getDayNum136())
          .eq(Objects.nonNull(obj.getDayNum137()), ApsGoodsForecastMakeProjectData::getDayNum137, obj.getDayNum137())
          .eq(Objects.nonNull(obj.getDayNum138()), ApsGoodsForecastMakeProjectData::getDayNum138, obj.getDayNum138())
          .eq(Objects.nonNull(obj.getDayNum139()), ApsGoodsForecastMakeProjectData::getDayNum139, obj.getDayNum139())
          .eq(Objects.nonNull(obj.getDayNum140()), ApsGoodsForecastMakeProjectData::getDayNum140, obj.getDayNum140())
          .eq(Objects.nonNull(obj.getDayNum141()), ApsGoodsForecastMakeProjectData::getDayNum141, obj.getDayNum141())
          .eq(Objects.nonNull(obj.getDayNum142()), ApsGoodsForecastMakeProjectData::getDayNum142, obj.getDayNum142())
          .eq(Objects.nonNull(obj.getDayNum143()), ApsGoodsForecastMakeProjectData::getDayNum143, obj.getDayNum143())
          .eq(Objects.nonNull(obj.getDayNum144()), ApsGoodsForecastMakeProjectData::getDayNum144, obj.getDayNum144())
          .eq(Objects.nonNull(obj.getDayNum145()), ApsGoodsForecastMakeProjectData::getDayNum145, obj.getDayNum145())
          .eq(Objects.nonNull(obj.getDayNum146()), ApsGoodsForecastMakeProjectData::getDayNum146, obj.getDayNum146())
          .eq(Objects.nonNull(obj.getDayNum147()), ApsGoodsForecastMakeProjectData::getDayNum147, obj.getDayNum147())
          .eq(Objects.nonNull(obj.getDayNum148()), ApsGoodsForecastMakeProjectData::getDayNum148, obj.getDayNum148())
          .eq(Objects.nonNull(obj.getDayNum149()), ApsGoodsForecastMakeProjectData::getDayNum149, obj.getDayNum149())
          .eq(Objects.nonNull(obj.getDayNum150()), ApsGoodsForecastMakeProjectData::getDayNum150, obj.getDayNum150())
          .eq(Objects.nonNull(obj.getDayNum151()), ApsGoodsForecastMakeProjectData::getDayNum151, obj.getDayNum151())
          .eq(Objects.nonNull(obj.getDayNum152()), ApsGoodsForecastMakeProjectData::getDayNum152, obj.getDayNum152())
          .eq(Objects.nonNull(obj.getDayNum153()), ApsGoodsForecastMakeProjectData::getDayNum153, obj.getDayNum153())
          .eq(Objects.nonNull(obj.getDayNum154()), ApsGoodsForecastMakeProjectData::getDayNum154, obj.getDayNum154())
          .eq(Objects.nonNull(obj.getDayNum155()), ApsGoodsForecastMakeProjectData::getDayNum155, obj.getDayNum155())
          .eq(Objects.nonNull(obj.getDayNum156()), ApsGoodsForecastMakeProjectData::getDayNum156, obj.getDayNum156())
          .eq(Objects.nonNull(obj.getDayNum157()), ApsGoodsForecastMakeProjectData::getDayNum157, obj.getDayNum157())
          .eq(Objects.nonNull(obj.getDayNum158()), ApsGoodsForecastMakeProjectData::getDayNum158, obj.getDayNum158())
          .eq(Objects.nonNull(obj.getDayNum159()), ApsGoodsForecastMakeProjectData::getDayNum159, obj.getDayNum159())
          .eq(Objects.nonNull(obj.getDayNum160()), ApsGoodsForecastMakeProjectData::getDayNum160, obj.getDayNum160())
          .eq(Objects.nonNull(obj.getDayNum161()), ApsGoodsForecastMakeProjectData::getDayNum161, obj.getDayNum161())
          .eq(Objects.nonNull(obj.getDayNum162()), ApsGoodsForecastMakeProjectData::getDayNum162, obj.getDayNum162())
          .eq(Objects.nonNull(obj.getDayNum163()), ApsGoodsForecastMakeProjectData::getDayNum163, obj.getDayNum163())
          .eq(Objects.nonNull(obj.getDayNum164()), ApsGoodsForecastMakeProjectData::getDayNum164, obj.getDayNum164())
          .eq(Objects.nonNull(obj.getDayNum165()), ApsGoodsForecastMakeProjectData::getDayNum165, obj.getDayNum165())
          .eq(Objects.nonNull(obj.getDayNum166()), ApsGoodsForecastMakeProjectData::getDayNum166, obj.getDayNum166())
          .eq(Objects.nonNull(obj.getDayNum167()), ApsGoodsForecastMakeProjectData::getDayNum167, obj.getDayNum167())
          .eq(Objects.nonNull(obj.getDayNum168()), ApsGoodsForecastMakeProjectData::getDayNum168, obj.getDayNum168())
          .eq(Objects.nonNull(obj.getDayNum169()), ApsGoodsForecastMakeProjectData::getDayNum169, obj.getDayNum169())
          .eq(Objects.nonNull(obj.getDayNum170()), ApsGoodsForecastMakeProjectData::getDayNum170, obj.getDayNum170())
          .eq(Objects.nonNull(obj.getDayNum171()), ApsGoodsForecastMakeProjectData::getDayNum171, obj.getDayNum171())
          .eq(Objects.nonNull(obj.getDayNum172()), ApsGoodsForecastMakeProjectData::getDayNum172, obj.getDayNum172())
          .eq(Objects.nonNull(obj.getDayNum173()), ApsGoodsForecastMakeProjectData::getDayNum173, obj.getDayNum173())
          .eq(Objects.nonNull(obj.getDayNum174()), ApsGoodsForecastMakeProjectData::getDayNum174, obj.getDayNum174())
          .eq(Objects.nonNull(obj.getDayNum175()), ApsGoodsForecastMakeProjectData::getDayNum175, obj.getDayNum175())
          .eq(Objects.nonNull(obj.getDayNum176()), ApsGoodsForecastMakeProjectData::getDayNum176, obj.getDayNum176())
          .eq(Objects.nonNull(obj.getDayNum177()), ApsGoodsForecastMakeProjectData::getDayNum177, obj.getDayNum177())
          .eq(Objects.nonNull(obj.getDayNum178()), ApsGoodsForecastMakeProjectData::getDayNum178, obj.getDayNum178())
          .eq(Objects.nonNull(obj.getDayNum179()), ApsGoodsForecastMakeProjectData::getDayNum179, obj.getDayNum179())
          .eq(Objects.nonNull(obj.getDayNum180()), ApsGoodsForecastMakeProjectData::getDayNum180, obj.getDayNum180())
          .eq(Objects.nonNull(obj.getDayNum181()), ApsGoodsForecastMakeProjectData::getDayNum181, obj.getDayNum181())
          .eq(Objects.nonNull(obj.getDayNum182()), ApsGoodsForecastMakeProjectData::getDayNum182, obj.getDayNum182())
          .eq(Objects.nonNull(obj.getDayNum183()), ApsGoodsForecastMakeProjectData::getDayNum183, obj.getDayNum183())
          .eq(Objects.nonNull(obj.getDayNum184()), ApsGoodsForecastMakeProjectData::getDayNum184, obj.getDayNum184())
          .eq(Objects.nonNull(obj.getDayNum185()), ApsGoodsForecastMakeProjectData::getDayNum185, obj.getDayNum185())
          .eq(Objects.nonNull(obj.getDayNum186()), ApsGoodsForecastMakeProjectData::getDayNum186, obj.getDayNum186())
          .eq(Objects.nonNull(obj.getDayNum187()), ApsGoodsForecastMakeProjectData::getDayNum187, obj.getDayNum187())
          .eq(Objects.nonNull(obj.getDayNum188()), ApsGoodsForecastMakeProjectData::getDayNum188, obj.getDayNum188())
          .eq(Objects.nonNull(obj.getDayNum189()), ApsGoodsForecastMakeProjectData::getDayNum189, obj.getDayNum189())
          .eq(Objects.nonNull(obj.getDayNum190()), ApsGoodsForecastMakeProjectData::getDayNum190, obj.getDayNum190())
          .eq(Objects.nonNull(obj.getDayNum191()), ApsGoodsForecastMakeProjectData::getDayNum191, obj.getDayNum191())
          .eq(Objects.nonNull(obj.getDayNum192()), ApsGoodsForecastMakeProjectData::getDayNum192, obj.getDayNum192())
          .eq(Objects.nonNull(obj.getDayNum193()), ApsGoodsForecastMakeProjectData::getDayNum193, obj.getDayNum193())
          .eq(Objects.nonNull(obj.getDayNum194()), ApsGoodsForecastMakeProjectData::getDayNum194, obj.getDayNum194())
          .eq(Objects.nonNull(obj.getDayNum195()), ApsGoodsForecastMakeProjectData::getDayNum195, obj.getDayNum195())
          .eq(Objects.nonNull(obj.getDayNum196()), ApsGoodsForecastMakeProjectData::getDayNum196, obj.getDayNum196())
          .eq(Objects.nonNull(obj.getDayNum197()), ApsGoodsForecastMakeProjectData::getDayNum197, obj.getDayNum197())
          .eq(Objects.nonNull(obj.getDayNum198()), ApsGoodsForecastMakeProjectData::getDayNum198, obj.getDayNum198())
          .eq(Objects.nonNull(obj.getDayNum199()), ApsGoodsForecastMakeProjectData::getDayNum199, obj.getDayNum199())
          .eq(Objects.nonNull(obj.getDayNum200()), ApsGoodsForecastMakeProjectData::getDayNum200, obj.getDayNum200())
          .eq(Objects.nonNull(obj.getDayNum201()), ApsGoodsForecastMakeProjectData::getDayNum201, obj.getDayNum201())
          .eq(Objects.nonNull(obj.getDayNum202()), ApsGoodsForecastMakeProjectData::getDayNum202, obj.getDayNum202())
          .eq(Objects.nonNull(obj.getDayNum203()), ApsGoodsForecastMakeProjectData::getDayNum203, obj.getDayNum203())
          .eq(Objects.nonNull(obj.getDayNum204()), ApsGoodsForecastMakeProjectData::getDayNum204, obj.getDayNum204())
          .eq(Objects.nonNull(obj.getDayNum205()), ApsGoodsForecastMakeProjectData::getDayNum205, obj.getDayNum205())
          .eq(Objects.nonNull(obj.getDayNum206()), ApsGoodsForecastMakeProjectData::getDayNum206, obj.getDayNum206())
          .eq(Objects.nonNull(obj.getDayNum207()), ApsGoodsForecastMakeProjectData::getDayNum207, obj.getDayNum207())
          .eq(Objects.nonNull(obj.getDayNum208()), ApsGoodsForecastMakeProjectData::getDayNum208, obj.getDayNum208())
          .eq(Objects.nonNull(obj.getDayNum209()), ApsGoodsForecastMakeProjectData::getDayNum209, obj.getDayNum209())
          .eq(Objects.nonNull(obj.getDayNum210()), ApsGoodsForecastMakeProjectData::getDayNum210, obj.getDayNum210())
          .eq(Objects.nonNull(obj.getDayNum211()), ApsGoodsForecastMakeProjectData::getDayNum211, obj.getDayNum211())
          .eq(Objects.nonNull(obj.getDayNum212()), ApsGoodsForecastMakeProjectData::getDayNum212, obj.getDayNum212())
          .eq(Objects.nonNull(obj.getDayNum213()), ApsGoodsForecastMakeProjectData::getDayNum213, obj.getDayNum213())
          .eq(Objects.nonNull(obj.getDayNum214()), ApsGoodsForecastMakeProjectData::getDayNum214, obj.getDayNum214())
          .eq(Objects.nonNull(obj.getDayNum215()), ApsGoodsForecastMakeProjectData::getDayNum215, obj.getDayNum215())
          .eq(Objects.nonNull(obj.getDayNum216()), ApsGoodsForecastMakeProjectData::getDayNum216, obj.getDayNum216())
          .eq(Objects.nonNull(obj.getDayNum217()), ApsGoodsForecastMakeProjectData::getDayNum217, obj.getDayNum217())
          .eq(Objects.nonNull(obj.getDayNum218()), ApsGoodsForecastMakeProjectData::getDayNum218, obj.getDayNum218())
          .eq(Objects.nonNull(obj.getDayNum219()), ApsGoodsForecastMakeProjectData::getDayNum219, obj.getDayNum219())
          .eq(Objects.nonNull(obj.getDayNum220()), ApsGoodsForecastMakeProjectData::getDayNum220, obj.getDayNum220())
          .eq(Objects.nonNull(obj.getDayNum221()), ApsGoodsForecastMakeProjectData::getDayNum221, obj.getDayNum221())
          .eq(Objects.nonNull(obj.getDayNum222()), ApsGoodsForecastMakeProjectData::getDayNum222, obj.getDayNum222())
          .eq(Objects.nonNull(obj.getDayNum223()), ApsGoodsForecastMakeProjectData::getDayNum223, obj.getDayNum223())
          .eq(Objects.nonNull(obj.getDayNum224()), ApsGoodsForecastMakeProjectData::getDayNum224, obj.getDayNum224())
          .eq(Objects.nonNull(obj.getDayNum225()), ApsGoodsForecastMakeProjectData::getDayNum225, obj.getDayNum225())
          .eq(Objects.nonNull(obj.getDayNum226()), ApsGoodsForecastMakeProjectData::getDayNum226, obj.getDayNum226())
          .eq(Objects.nonNull(obj.getDayNum227()), ApsGoodsForecastMakeProjectData::getDayNum227, obj.getDayNum227())
          .eq(Objects.nonNull(obj.getDayNum228()), ApsGoodsForecastMakeProjectData::getDayNum228, obj.getDayNum228())
          .eq(Objects.nonNull(obj.getDayNum229()), ApsGoodsForecastMakeProjectData::getDayNum229, obj.getDayNum229())
          .eq(Objects.nonNull(obj.getDayNum230()), ApsGoodsForecastMakeProjectData::getDayNum230, obj.getDayNum230())
          .eq(Objects.nonNull(obj.getDayNum231()), ApsGoodsForecastMakeProjectData::getDayNum231, obj.getDayNum231())
          .eq(Objects.nonNull(obj.getDayNum232()), ApsGoodsForecastMakeProjectData::getDayNum232, obj.getDayNum232())
          .eq(Objects.nonNull(obj.getDayNum233()), ApsGoodsForecastMakeProjectData::getDayNum233, obj.getDayNum233())
          .eq(Objects.nonNull(obj.getDayNum234()), ApsGoodsForecastMakeProjectData::getDayNum234, obj.getDayNum234())
          .eq(Objects.nonNull(obj.getDayNum235()), ApsGoodsForecastMakeProjectData::getDayNum235, obj.getDayNum235())
          .eq(Objects.nonNull(obj.getDayNum236()), ApsGoodsForecastMakeProjectData::getDayNum236, obj.getDayNum236())
          .eq(Objects.nonNull(obj.getDayNum237()), ApsGoodsForecastMakeProjectData::getDayNum237, obj.getDayNum237())
          .eq(Objects.nonNull(obj.getDayNum238()), ApsGoodsForecastMakeProjectData::getDayNum238, obj.getDayNum238())
          .eq(Objects.nonNull(obj.getDayNum239()), ApsGoodsForecastMakeProjectData::getDayNum239, obj.getDayNum239())
          .eq(Objects.nonNull(obj.getDayNum240()), ApsGoodsForecastMakeProjectData::getDayNum240, obj.getDayNum240())
          .eq(Objects.nonNull(obj.getDayNum241()), ApsGoodsForecastMakeProjectData::getDayNum241, obj.getDayNum241())
          .eq(Objects.nonNull(obj.getDayNum242()), ApsGoodsForecastMakeProjectData::getDayNum242, obj.getDayNum242())
          .eq(Objects.nonNull(obj.getDayNum243()), ApsGoodsForecastMakeProjectData::getDayNum243, obj.getDayNum243())
          .eq(Objects.nonNull(obj.getDayNum244()), ApsGoodsForecastMakeProjectData::getDayNum244, obj.getDayNum244())
          .eq(Objects.nonNull(obj.getDayNum245()), ApsGoodsForecastMakeProjectData::getDayNum245, obj.getDayNum245())
          .eq(Objects.nonNull(obj.getDayNum246()), ApsGoodsForecastMakeProjectData::getDayNum246, obj.getDayNum246())
          .eq(Objects.nonNull(obj.getDayNum247()), ApsGoodsForecastMakeProjectData::getDayNum247, obj.getDayNum247())
          .eq(Objects.nonNull(obj.getDayNum248()), ApsGoodsForecastMakeProjectData::getDayNum248, obj.getDayNum248())
          .eq(Objects.nonNull(obj.getDayNum249()), ApsGoodsForecastMakeProjectData::getDayNum249, obj.getDayNum249())
          .eq(Objects.nonNull(obj.getDayNum250()), ApsGoodsForecastMakeProjectData::getDayNum250, obj.getDayNum250())
          .eq(Objects.nonNull(obj.getDayNum251()), ApsGoodsForecastMakeProjectData::getDayNum251, obj.getDayNum251())
          .eq(Objects.nonNull(obj.getDayNum252()), ApsGoodsForecastMakeProjectData::getDayNum252, obj.getDayNum252())
          .eq(Objects.nonNull(obj.getDayNum253()), ApsGoodsForecastMakeProjectData::getDayNum253, obj.getDayNum253())
          .eq(Objects.nonNull(obj.getDayNum254()), ApsGoodsForecastMakeProjectData::getDayNum254, obj.getDayNum254())
          .eq(Objects.nonNull(obj.getDayNum255()), ApsGoodsForecastMakeProjectData::getDayNum255, obj.getDayNum255())
          .eq(Objects.nonNull(obj.getDayNum256()), ApsGoodsForecastMakeProjectData::getDayNum256, obj.getDayNum256())
          .eq(Objects.nonNull(obj.getDayNum257()), ApsGoodsForecastMakeProjectData::getDayNum257, obj.getDayNum257())
          .eq(Objects.nonNull(obj.getDayNum258()), ApsGoodsForecastMakeProjectData::getDayNum258, obj.getDayNum258())
          .eq(Objects.nonNull(obj.getDayNum259()), ApsGoodsForecastMakeProjectData::getDayNum259, obj.getDayNum259())
          .eq(Objects.nonNull(obj.getDayNum260()), ApsGoodsForecastMakeProjectData::getDayNum260, obj.getDayNum260())
          .eq(Objects.nonNull(obj.getDayNum261()), ApsGoodsForecastMakeProjectData::getDayNum261, obj.getDayNum261())
          .eq(Objects.nonNull(obj.getDayNum262()), ApsGoodsForecastMakeProjectData::getDayNum262, obj.getDayNum262())
          .eq(Objects.nonNull(obj.getDayNum263()), ApsGoodsForecastMakeProjectData::getDayNum263, obj.getDayNum263())
          .eq(Objects.nonNull(obj.getDayNum264()), ApsGoodsForecastMakeProjectData::getDayNum264, obj.getDayNum264())
          .eq(Objects.nonNull(obj.getDayNum265()), ApsGoodsForecastMakeProjectData::getDayNum265, obj.getDayNum265())
          .eq(Objects.nonNull(obj.getDayNum266()), ApsGoodsForecastMakeProjectData::getDayNum266, obj.getDayNum266())
          .eq(Objects.nonNull(obj.getDayNum267()), ApsGoodsForecastMakeProjectData::getDayNum267, obj.getDayNum267())
          .eq(Objects.nonNull(obj.getDayNum268()), ApsGoodsForecastMakeProjectData::getDayNum268, obj.getDayNum268())
          .eq(Objects.nonNull(obj.getDayNum269()), ApsGoodsForecastMakeProjectData::getDayNum269, obj.getDayNum269())
          .eq(Objects.nonNull(obj.getDayNum270()), ApsGoodsForecastMakeProjectData::getDayNum270, obj.getDayNum270())
          .eq(Objects.nonNull(obj.getDayNum271()), ApsGoodsForecastMakeProjectData::getDayNum271, obj.getDayNum271())
          .eq(Objects.nonNull(obj.getDayNum272()), ApsGoodsForecastMakeProjectData::getDayNum272, obj.getDayNum272())
          .eq(Objects.nonNull(obj.getDayNum273()), ApsGoodsForecastMakeProjectData::getDayNum273, obj.getDayNum273())
          .eq(Objects.nonNull(obj.getDayNum274()), ApsGoodsForecastMakeProjectData::getDayNum274, obj.getDayNum274())
          .eq(Objects.nonNull(obj.getDayNum275()), ApsGoodsForecastMakeProjectData::getDayNum275, obj.getDayNum275())
          .eq(Objects.nonNull(obj.getDayNum276()), ApsGoodsForecastMakeProjectData::getDayNum276, obj.getDayNum276())
          .eq(Objects.nonNull(obj.getDayNum277()), ApsGoodsForecastMakeProjectData::getDayNum277, obj.getDayNum277())
          .eq(Objects.nonNull(obj.getDayNum278()), ApsGoodsForecastMakeProjectData::getDayNum278, obj.getDayNum278())
          .eq(Objects.nonNull(obj.getDayNum279()), ApsGoodsForecastMakeProjectData::getDayNum279, obj.getDayNum279())
          .eq(Objects.nonNull(obj.getDayNum280()), ApsGoodsForecastMakeProjectData::getDayNum280, obj.getDayNum280())
          .eq(Objects.nonNull(obj.getDayNum281()), ApsGoodsForecastMakeProjectData::getDayNum281, obj.getDayNum281())
          .eq(Objects.nonNull(obj.getDayNum282()), ApsGoodsForecastMakeProjectData::getDayNum282, obj.getDayNum282())
          .eq(Objects.nonNull(obj.getDayNum283()), ApsGoodsForecastMakeProjectData::getDayNum283, obj.getDayNum283())
          .eq(Objects.nonNull(obj.getDayNum284()), ApsGoodsForecastMakeProjectData::getDayNum284, obj.getDayNum284())
          .eq(Objects.nonNull(obj.getDayNum285()), ApsGoodsForecastMakeProjectData::getDayNum285, obj.getDayNum285())
          .eq(Objects.nonNull(obj.getDayNum286()), ApsGoodsForecastMakeProjectData::getDayNum286, obj.getDayNum286())
          .eq(Objects.nonNull(obj.getDayNum287()), ApsGoodsForecastMakeProjectData::getDayNum287, obj.getDayNum287())
          .eq(Objects.nonNull(obj.getDayNum288()), ApsGoodsForecastMakeProjectData::getDayNum288, obj.getDayNum288())
          .eq(Objects.nonNull(obj.getDayNum289()), ApsGoodsForecastMakeProjectData::getDayNum289, obj.getDayNum289())
          .eq(Objects.nonNull(obj.getDayNum290()), ApsGoodsForecastMakeProjectData::getDayNum290, obj.getDayNum290())
          .eq(Objects.nonNull(obj.getDayNum291()), ApsGoodsForecastMakeProjectData::getDayNum291, obj.getDayNum291())
          .eq(Objects.nonNull(obj.getDayNum292()), ApsGoodsForecastMakeProjectData::getDayNum292, obj.getDayNum292())
          .eq(Objects.nonNull(obj.getDayNum293()), ApsGoodsForecastMakeProjectData::getDayNum293, obj.getDayNum293())
          .eq(Objects.nonNull(obj.getDayNum294()), ApsGoodsForecastMakeProjectData::getDayNum294, obj.getDayNum294())
          .eq(Objects.nonNull(obj.getDayNum295()), ApsGoodsForecastMakeProjectData::getDayNum295, obj.getDayNum295())
          .eq(Objects.nonNull(obj.getDayNum296()), ApsGoodsForecastMakeProjectData::getDayNum296, obj.getDayNum296())
          .eq(Objects.nonNull(obj.getDayNum297()), ApsGoodsForecastMakeProjectData::getDayNum297, obj.getDayNum297())
          .eq(Objects.nonNull(obj.getDayNum298()), ApsGoodsForecastMakeProjectData::getDayNum298, obj.getDayNum298())
          .eq(Objects.nonNull(obj.getDayNum299()), ApsGoodsForecastMakeProjectData::getDayNum299, obj.getDayNum299())
          .eq(Objects.nonNull(obj.getDayNum300()), ApsGoodsForecastMakeProjectData::getDayNum300, obj.getDayNum300())
          .eq(Objects.nonNull(obj.getDayNum301()), ApsGoodsForecastMakeProjectData::getDayNum301, obj.getDayNum301())
          .eq(Objects.nonNull(obj.getDayNum302()), ApsGoodsForecastMakeProjectData::getDayNum302, obj.getDayNum302())
          .eq(Objects.nonNull(obj.getDayNum303()), ApsGoodsForecastMakeProjectData::getDayNum303, obj.getDayNum303())
          .eq(Objects.nonNull(obj.getDayNum304()), ApsGoodsForecastMakeProjectData::getDayNum304, obj.getDayNum304())
          .eq(Objects.nonNull(obj.getDayNum305()), ApsGoodsForecastMakeProjectData::getDayNum305, obj.getDayNum305())
          .eq(Objects.nonNull(obj.getDayNum306()), ApsGoodsForecastMakeProjectData::getDayNum306, obj.getDayNum306())
          .eq(Objects.nonNull(obj.getDayNum307()), ApsGoodsForecastMakeProjectData::getDayNum307, obj.getDayNum307())
          .eq(Objects.nonNull(obj.getDayNum308()), ApsGoodsForecastMakeProjectData::getDayNum308, obj.getDayNum308())
          .eq(Objects.nonNull(obj.getDayNum309()), ApsGoodsForecastMakeProjectData::getDayNum309, obj.getDayNum309())
          .eq(Objects.nonNull(obj.getDayNum310()), ApsGoodsForecastMakeProjectData::getDayNum310, obj.getDayNum310())
          .eq(Objects.nonNull(obj.getDayNum311()), ApsGoodsForecastMakeProjectData::getDayNum311, obj.getDayNum311())
          .eq(Objects.nonNull(obj.getDayNum312()), ApsGoodsForecastMakeProjectData::getDayNum312, obj.getDayNum312())
          .eq(Objects.nonNull(obj.getDayNum313()), ApsGoodsForecastMakeProjectData::getDayNum313, obj.getDayNum313())
          .eq(Objects.nonNull(obj.getDayNum314()), ApsGoodsForecastMakeProjectData::getDayNum314, obj.getDayNum314())
          .eq(Objects.nonNull(obj.getDayNum315()), ApsGoodsForecastMakeProjectData::getDayNum315, obj.getDayNum315())
          .eq(Objects.nonNull(obj.getDayNum316()), ApsGoodsForecastMakeProjectData::getDayNum316, obj.getDayNum316())
          .eq(Objects.nonNull(obj.getDayNum317()), ApsGoodsForecastMakeProjectData::getDayNum317, obj.getDayNum317())
          .eq(Objects.nonNull(obj.getDayNum318()), ApsGoodsForecastMakeProjectData::getDayNum318, obj.getDayNum318())
          .eq(Objects.nonNull(obj.getDayNum319()), ApsGoodsForecastMakeProjectData::getDayNum319, obj.getDayNum319())
          .eq(Objects.nonNull(obj.getDayNum320()), ApsGoodsForecastMakeProjectData::getDayNum320, obj.getDayNum320())
          .eq(Objects.nonNull(obj.getDayNum321()), ApsGoodsForecastMakeProjectData::getDayNum321, obj.getDayNum321())
          .eq(Objects.nonNull(obj.getDayNum322()), ApsGoodsForecastMakeProjectData::getDayNum322, obj.getDayNum322())
          .eq(Objects.nonNull(obj.getDayNum323()), ApsGoodsForecastMakeProjectData::getDayNum323, obj.getDayNum323())
          .eq(Objects.nonNull(obj.getDayNum324()), ApsGoodsForecastMakeProjectData::getDayNum324, obj.getDayNum324())
          .eq(Objects.nonNull(obj.getDayNum325()), ApsGoodsForecastMakeProjectData::getDayNum325, obj.getDayNum325())
          .eq(Objects.nonNull(obj.getDayNum326()), ApsGoodsForecastMakeProjectData::getDayNum326, obj.getDayNum326())
          .eq(Objects.nonNull(obj.getDayNum327()), ApsGoodsForecastMakeProjectData::getDayNum327, obj.getDayNum327())
          .eq(Objects.nonNull(obj.getDayNum328()), ApsGoodsForecastMakeProjectData::getDayNum328, obj.getDayNum328())
          .eq(Objects.nonNull(obj.getDayNum329()), ApsGoodsForecastMakeProjectData::getDayNum329, obj.getDayNum329())
          .eq(Objects.nonNull(obj.getDayNum330()), ApsGoodsForecastMakeProjectData::getDayNum330, obj.getDayNum330())
          .eq(Objects.nonNull(obj.getDayNum331()), ApsGoodsForecastMakeProjectData::getDayNum331, obj.getDayNum331())
          .eq(Objects.nonNull(obj.getDayNum332()), ApsGoodsForecastMakeProjectData::getDayNum332, obj.getDayNum332())
          .eq(Objects.nonNull(obj.getDayNum333()), ApsGoodsForecastMakeProjectData::getDayNum333, obj.getDayNum333())
          .eq(Objects.nonNull(obj.getDayNum334()), ApsGoodsForecastMakeProjectData::getDayNum334, obj.getDayNum334())
          .eq(Objects.nonNull(obj.getDayNum335()), ApsGoodsForecastMakeProjectData::getDayNum335, obj.getDayNum335())
          .eq(Objects.nonNull(obj.getDayNum336()), ApsGoodsForecastMakeProjectData::getDayNum336, obj.getDayNum336())
          .eq(Objects.nonNull(obj.getDayNum337()), ApsGoodsForecastMakeProjectData::getDayNum337, obj.getDayNum337())
          .eq(Objects.nonNull(obj.getDayNum338()), ApsGoodsForecastMakeProjectData::getDayNum338, obj.getDayNum338())
          .eq(Objects.nonNull(obj.getDayNum339()), ApsGoodsForecastMakeProjectData::getDayNum339, obj.getDayNum339())
          .eq(Objects.nonNull(obj.getDayNum340()), ApsGoodsForecastMakeProjectData::getDayNum340, obj.getDayNum340())
          .eq(Objects.nonNull(obj.getDayNum341()), ApsGoodsForecastMakeProjectData::getDayNum341, obj.getDayNum341())
          .eq(Objects.nonNull(obj.getDayNum342()), ApsGoodsForecastMakeProjectData::getDayNum342, obj.getDayNum342())
          .eq(Objects.nonNull(obj.getDayNum343()), ApsGoodsForecastMakeProjectData::getDayNum343, obj.getDayNum343())
          .eq(Objects.nonNull(obj.getDayNum344()), ApsGoodsForecastMakeProjectData::getDayNum344, obj.getDayNum344())
          .eq(Objects.nonNull(obj.getDayNum345()), ApsGoodsForecastMakeProjectData::getDayNum345, obj.getDayNum345())
          .eq(Objects.nonNull(obj.getDayNum346()), ApsGoodsForecastMakeProjectData::getDayNum346, obj.getDayNum346())
          .eq(Objects.nonNull(obj.getDayNum347()), ApsGoodsForecastMakeProjectData::getDayNum347, obj.getDayNum347())
          .eq(Objects.nonNull(obj.getDayNum348()), ApsGoodsForecastMakeProjectData::getDayNum348, obj.getDayNum348())
          .eq(Objects.nonNull(obj.getDayNum349()), ApsGoodsForecastMakeProjectData::getDayNum349, obj.getDayNum349())
          .eq(Objects.nonNull(obj.getDayNum350()), ApsGoodsForecastMakeProjectData::getDayNum350, obj.getDayNum350())
          .eq(Objects.nonNull(obj.getDayNum351()), ApsGoodsForecastMakeProjectData::getDayNum351, obj.getDayNum351())
          .eq(Objects.nonNull(obj.getDayNum352()), ApsGoodsForecastMakeProjectData::getDayNum352, obj.getDayNum352())
          .eq(Objects.nonNull(obj.getDayNum353()), ApsGoodsForecastMakeProjectData::getDayNum353, obj.getDayNum353())
          .eq(Objects.nonNull(obj.getDayNum354()), ApsGoodsForecastMakeProjectData::getDayNum354, obj.getDayNum354())
          .eq(Objects.nonNull(obj.getDayNum355()), ApsGoodsForecastMakeProjectData::getDayNum355, obj.getDayNum355())
          .eq(Objects.nonNull(obj.getDayNum356()), ApsGoodsForecastMakeProjectData::getDayNum356, obj.getDayNum356())
          .eq(Objects.nonNull(obj.getDayNum357()), ApsGoodsForecastMakeProjectData::getDayNum357, obj.getDayNum357())
          .eq(Objects.nonNull(obj.getDayNum358()), ApsGoodsForecastMakeProjectData::getDayNum358, obj.getDayNum358())
          .eq(Objects.nonNull(obj.getDayNum359()), ApsGoodsForecastMakeProjectData::getDayNum359, obj.getDayNum359())
          .eq(Objects.nonNull(obj.getDayNum360()), ApsGoodsForecastMakeProjectData::getDayNum360, obj.getDayNum360())
          .eq(Objects.nonNull(obj.getDayNum361()), ApsGoodsForecastMakeProjectData::getDayNum361, obj.getDayNum361())
          .eq(Objects.nonNull(obj.getDayNum362()), ApsGoodsForecastMakeProjectData::getDayNum362, obj.getDayNum362())
          .eq(Objects.nonNull(obj.getDayNum363()), ApsGoodsForecastMakeProjectData::getDayNum363, obj.getDayNum363())
          .eq(Objects.nonNull(obj.getDayNum364()), ApsGoodsForecastMakeProjectData::getDayNum364, obj.getDayNum364())
          .eq(Objects.nonNull(obj.getDayNum365()), ApsGoodsForecastMakeProjectData::getDayNum365, obj.getDayNum365())
          .eq(Objects.nonNull(obj.getDayNum366()), ApsGoodsForecastMakeProjectData::getDayNum366, obj.getDayNum366())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMakeProjectData::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMakeProjectData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMakeProjectData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMakeProjectDataService#queryPageList");

  }


}

