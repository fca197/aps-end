package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMakeBomUseMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeBomUse;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeBomUseService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (ApsGoodsForecastMakeBomUse)表服务实现类
 *
 * @author peanut
 * @since 2024-05-15 10:26:04
 */
@Service("apsGoodsForecastMakeBomUseService")
@Transactional
public class ApsGoodsForecastMakeBomUseServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMakeBomUseMapper, ApsGoodsForecastMakeBomUse> implements
    ApsGoodsForecastMakeBomUseService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMakeBomUseQueryListRes queryList(ApsGoodsForecastMakeBomUseQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeBomUse> list = this.list(q);

    List<ApsGoodsForecastMakeBomUseDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMakeBomUseDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsGoodsForecastMakeBomUseServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsForecastMakeBomUseQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeBomUseExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMakeBomUse> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMakeBomUse> list = this.page(page, q);
      IPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMakeBomUseServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMakeBomUseDto> apsGoodsForecastMakeBomUseDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMakeBomUseDtoList)) {
      return;
    }


  }


  private MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> getWrapper(ApsGoodsForecastMakeBomUseDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMakeBomUse::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMakeMonthId()), ApsGoodsForecastMakeBomUse::getMakeMonthId, obj.getMakeMonthId())
          .eq(Objects.nonNull(obj.getBomId()), ApsGoodsForecastMakeBomUse::getBomId, obj.getBomId())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMakeBomUse::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getDayNum1()), ApsGoodsForecastMakeBomUse::getDayNum1, obj.getDayNum1())
          .eq(Objects.nonNull(obj.getDayNum2()), ApsGoodsForecastMakeBomUse::getDayNum2, obj.getDayNum2())
          .eq(Objects.nonNull(obj.getDayNum3()), ApsGoodsForecastMakeBomUse::getDayNum3, obj.getDayNum3())
          .eq(Objects.nonNull(obj.getDayNum4()), ApsGoodsForecastMakeBomUse::getDayNum4, obj.getDayNum4())
          .eq(Objects.nonNull(obj.getDayNum5()), ApsGoodsForecastMakeBomUse::getDayNum5, obj.getDayNum5())
          .eq(Objects.nonNull(obj.getDayNum6()), ApsGoodsForecastMakeBomUse::getDayNum6, obj.getDayNum6())
          .eq(Objects.nonNull(obj.getDayNum7()), ApsGoodsForecastMakeBomUse::getDayNum7, obj.getDayNum7())
          .eq(Objects.nonNull(obj.getDayNum8()), ApsGoodsForecastMakeBomUse::getDayNum8, obj.getDayNum8())
          .eq(Objects.nonNull(obj.getDayNum9()), ApsGoodsForecastMakeBomUse::getDayNum9, obj.getDayNum9())
          .eq(Objects.nonNull(obj.getDayNum10()), ApsGoodsForecastMakeBomUse::getDayNum10, obj.getDayNum10())
          .eq(Objects.nonNull(obj.getDayNum11()), ApsGoodsForecastMakeBomUse::getDayNum11, obj.getDayNum11())
          .eq(Objects.nonNull(obj.getDayNum12()), ApsGoodsForecastMakeBomUse::getDayNum12, obj.getDayNum12())
          .eq(Objects.nonNull(obj.getDayNum13()), ApsGoodsForecastMakeBomUse::getDayNum13, obj.getDayNum13())
          .eq(Objects.nonNull(obj.getDayNum14()), ApsGoodsForecastMakeBomUse::getDayNum14, obj.getDayNum14())
          .eq(Objects.nonNull(obj.getDayNum15()), ApsGoodsForecastMakeBomUse::getDayNum15, obj.getDayNum15())
          .eq(Objects.nonNull(obj.getDayNum16()), ApsGoodsForecastMakeBomUse::getDayNum16, obj.getDayNum16())
          .eq(Objects.nonNull(obj.getDayNum17()), ApsGoodsForecastMakeBomUse::getDayNum17, obj.getDayNum17())
          .eq(Objects.nonNull(obj.getDayNum18()), ApsGoodsForecastMakeBomUse::getDayNum18, obj.getDayNum18())
          .eq(Objects.nonNull(obj.getDayNum19()), ApsGoodsForecastMakeBomUse::getDayNum19, obj.getDayNum19())
          .eq(Objects.nonNull(obj.getDayNum20()), ApsGoodsForecastMakeBomUse::getDayNum20, obj.getDayNum20())
          .eq(Objects.nonNull(obj.getDayNum21()), ApsGoodsForecastMakeBomUse::getDayNum21, obj.getDayNum21())
          .eq(Objects.nonNull(obj.getDayNum22()), ApsGoodsForecastMakeBomUse::getDayNum22, obj.getDayNum22())
          .eq(Objects.nonNull(obj.getDayNum23()), ApsGoodsForecastMakeBomUse::getDayNum23, obj.getDayNum23())
          .eq(Objects.nonNull(obj.getDayNum24()), ApsGoodsForecastMakeBomUse::getDayNum24, obj.getDayNum24())
          .eq(Objects.nonNull(obj.getDayNum25()), ApsGoodsForecastMakeBomUse::getDayNum25, obj.getDayNum25())
          .eq(Objects.nonNull(obj.getDayNum26()), ApsGoodsForecastMakeBomUse::getDayNum26, obj.getDayNum26())
          .eq(Objects.nonNull(obj.getDayNum27()), ApsGoodsForecastMakeBomUse::getDayNum27, obj.getDayNum27())
          .eq(Objects.nonNull(obj.getDayNum28()), ApsGoodsForecastMakeBomUse::getDayNum28, obj.getDayNum28())
          .eq(Objects.nonNull(obj.getDayNum29()), ApsGoodsForecastMakeBomUse::getDayNum29, obj.getDayNum29())
          .eq(Objects.nonNull(obj.getDayNum30()), ApsGoodsForecastMakeBomUse::getDayNum30, obj.getDayNum30())
          .eq(Objects.nonNull(obj.getDayNum31()), ApsGoodsForecastMakeBomUse::getDayNum31, obj.getDayNum31())
          .eq(Objects.nonNull(obj.getDayNum32()), ApsGoodsForecastMakeBomUse::getDayNum32, obj.getDayNum32())
          .eq(Objects.nonNull(obj.getDayNum33()), ApsGoodsForecastMakeBomUse::getDayNum33, obj.getDayNum33())
          .eq(Objects.nonNull(obj.getDayNum34()), ApsGoodsForecastMakeBomUse::getDayNum34, obj.getDayNum34())
          .eq(Objects.nonNull(obj.getDayNum35()), ApsGoodsForecastMakeBomUse::getDayNum35, obj.getDayNum35())
          .eq(Objects.nonNull(obj.getDayNum36()), ApsGoodsForecastMakeBomUse::getDayNum36, obj.getDayNum36())
          .eq(Objects.nonNull(obj.getDayNum37()), ApsGoodsForecastMakeBomUse::getDayNum37, obj.getDayNum37())
          .eq(Objects.nonNull(obj.getDayNum38()), ApsGoodsForecastMakeBomUse::getDayNum38, obj.getDayNum38())
          .eq(Objects.nonNull(obj.getDayNum39()), ApsGoodsForecastMakeBomUse::getDayNum39, obj.getDayNum39())
          .eq(Objects.nonNull(obj.getDayNum40()), ApsGoodsForecastMakeBomUse::getDayNum40, obj.getDayNum40())
          .eq(Objects.nonNull(obj.getDayNum41()), ApsGoodsForecastMakeBomUse::getDayNum41, obj.getDayNum41())
          .eq(Objects.nonNull(obj.getDayNum42()), ApsGoodsForecastMakeBomUse::getDayNum42, obj.getDayNum42())
          .eq(Objects.nonNull(obj.getDayNum43()), ApsGoodsForecastMakeBomUse::getDayNum43, obj.getDayNum43())
          .eq(Objects.nonNull(obj.getDayNum44()), ApsGoodsForecastMakeBomUse::getDayNum44, obj.getDayNum44())
          .eq(Objects.nonNull(obj.getDayNum45()), ApsGoodsForecastMakeBomUse::getDayNum45, obj.getDayNum45())
          .eq(Objects.nonNull(obj.getDayNum46()), ApsGoodsForecastMakeBomUse::getDayNum46, obj.getDayNum46())
          .eq(Objects.nonNull(obj.getDayNum47()), ApsGoodsForecastMakeBomUse::getDayNum47, obj.getDayNum47())
          .eq(Objects.nonNull(obj.getDayNum48()), ApsGoodsForecastMakeBomUse::getDayNum48, obj.getDayNum48())
          .eq(Objects.nonNull(obj.getDayNum49()), ApsGoodsForecastMakeBomUse::getDayNum49, obj.getDayNum49())
          .eq(Objects.nonNull(obj.getDayNum50()), ApsGoodsForecastMakeBomUse::getDayNum50, obj.getDayNum50())
          .eq(Objects.nonNull(obj.getDayNum51()), ApsGoodsForecastMakeBomUse::getDayNum51, obj.getDayNum51())
          .eq(Objects.nonNull(obj.getDayNum52()), ApsGoodsForecastMakeBomUse::getDayNum52, obj.getDayNum52())
          .eq(Objects.nonNull(obj.getDayNum53()), ApsGoodsForecastMakeBomUse::getDayNum53, obj.getDayNum53())
          .eq(Objects.nonNull(obj.getDayNum54()), ApsGoodsForecastMakeBomUse::getDayNum54, obj.getDayNum54())
          .eq(Objects.nonNull(obj.getDayNum55()), ApsGoodsForecastMakeBomUse::getDayNum55, obj.getDayNum55())
          .eq(Objects.nonNull(obj.getDayNum56()), ApsGoodsForecastMakeBomUse::getDayNum56, obj.getDayNum56())
          .eq(Objects.nonNull(obj.getDayNum57()), ApsGoodsForecastMakeBomUse::getDayNum57, obj.getDayNum57())
          .eq(Objects.nonNull(obj.getDayNum58()), ApsGoodsForecastMakeBomUse::getDayNum58, obj.getDayNum58())
          .eq(Objects.nonNull(obj.getDayNum59()), ApsGoodsForecastMakeBomUse::getDayNum59, obj.getDayNum59())
          .eq(Objects.nonNull(obj.getDayNum60()), ApsGoodsForecastMakeBomUse::getDayNum60, obj.getDayNum60())
          .eq(Objects.nonNull(obj.getDayNum61()), ApsGoodsForecastMakeBomUse::getDayNum61, obj.getDayNum61())
          .eq(Objects.nonNull(obj.getDayNum62()), ApsGoodsForecastMakeBomUse::getDayNum62, obj.getDayNum62())
          .eq(Objects.nonNull(obj.getDayNum63()), ApsGoodsForecastMakeBomUse::getDayNum63, obj.getDayNum63())
          .eq(Objects.nonNull(obj.getDayNum64()), ApsGoodsForecastMakeBomUse::getDayNum64, obj.getDayNum64())
          .eq(Objects.nonNull(obj.getDayNum65()), ApsGoodsForecastMakeBomUse::getDayNum65, obj.getDayNum65())
          .eq(Objects.nonNull(obj.getDayNum66()), ApsGoodsForecastMakeBomUse::getDayNum66, obj.getDayNum66())
          .eq(Objects.nonNull(obj.getDayNum67()), ApsGoodsForecastMakeBomUse::getDayNum67, obj.getDayNum67())
          .eq(Objects.nonNull(obj.getDayNum68()), ApsGoodsForecastMakeBomUse::getDayNum68, obj.getDayNum68())
          .eq(Objects.nonNull(obj.getDayNum69()), ApsGoodsForecastMakeBomUse::getDayNum69, obj.getDayNum69())
          .eq(Objects.nonNull(obj.getDayNum70()), ApsGoodsForecastMakeBomUse::getDayNum70, obj.getDayNum70())
          .eq(Objects.nonNull(obj.getDayNum71()), ApsGoodsForecastMakeBomUse::getDayNum71, obj.getDayNum71())
          .eq(Objects.nonNull(obj.getDayNum72()), ApsGoodsForecastMakeBomUse::getDayNum72, obj.getDayNum72())
          .eq(Objects.nonNull(obj.getDayNum73()), ApsGoodsForecastMakeBomUse::getDayNum73, obj.getDayNum73())
          .eq(Objects.nonNull(obj.getDayNum74()), ApsGoodsForecastMakeBomUse::getDayNum74, obj.getDayNum74())
          .eq(Objects.nonNull(obj.getDayNum75()), ApsGoodsForecastMakeBomUse::getDayNum75, obj.getDayNum75())
          .eq(Objects.nonNull(obj.getDayNum76()), ApsGoodsForecastMakeBomUse::getDayNum76, obj.getDayNum76())
          .eq(Objects.nonNull(obj.getDayNum77()), ApsGoodsForecastMakeBomUse::getDayNum77, obj.getDayNum77())
          .eq(Objects.nonNull(obj.getDayNum78()), ApsGoodsForecastMakeBomUse::getDayNum78, obj.getDayNum78())
          .eq(Objects.nonNull(obj.getDayNum79()), ApsGoodsForecastMakeBomUse::getDayNum79, obj.getDayNum79())
          .eq(Objects.nonNull(obj.getDayNum80()), ApsGoodsForecastMakeBomUse::getDayNum80, obj.getDayNum80())
          .eq(Objects.nonNull(obj.getDayNum81()), ApsGoodsForecastMakeBomUse::getDayNum81, obj.getDayNum81())
          .eq(Objects.nonNull(obj.getDayNum82()), ApsGoodsForecastMakeBomUse::getDayNum82, obj.getDayNum82())
          .eq(Objects.nonNull(obj.getDayNum83()), ApsGoodsForecastMakeBomUse::getDayNum83, obj.getDayNum83())
          .eq(Objects.nonNull(obj.getDayNum84()), ApsGoodsForecastMakeBomUse::getDayNum84, obj.getDayNum84())
          .eq(Objects.nonNull(obj.getDayNum85()), ApsGoodsForecastMakeBomUse::getDayNum85, obj.getDayNum85())
          .eq(Objects.nonNull(obj.getDayNum86()), ApsGoodsForecastMakeBomUse::getDayNum86, obj.getDayNum86())
          .eq(Objects.nonNull(obj.getDayNum87()), ApsGoodsForecastMakeBomUse::getDayNum87, obj.getDayNum87())
          .eq(Objects.nonNull(obj.getDayNum88()), ApsGoodsForecastMakeBomUse::getDayNum88, obj.getDayNum88())
          .eq(Objects.nonNull(obj.getDayNum89()), ApsGoodsForecastMakeBomUse::getDayNum89, obj.getDayNum89())
          .eq(Objects.nonNull(obj.getDayNum90()), ApsGoodsForecastMakeBomUse::getDayNum90, obj.getDayNum90())
          .eq(Objects.nonNull(obj.getDayNum91()), ApsGoodsForecastMakeBomUse::getDayNum91, obj.getDayNum91())
          .eq(Objects.nonNull(obj.getDayNum92()), ApsGoodsForecastMakeBomUse::getDayNum92, obj.getDayNum92())
          .eq(Objects.nonNull(obj.getDayNum93()), ApsGoodsForecastMakeBomUse::getDayNum93, obj.getDayNum93())
          .eq(Objects.nonNull(obj.getDayNum94()), ApsGoodsForecastMakeBomUse::getDayNum94, obj.getDayNum94())
          .eq(Objects.nonNull(obj.getDayNum95()), ApsGoodsForecastMakeBomUse::getDayNum95, obj.getDayNum95())
          .eq(Objects.nonNull(obj.getDayNum96()), ApsGoodsForecastMakeBomUse::getDayNum96, obj.getDayNum96())
          .eq(Objects.nonNull(obj.getDayNum97()), ApsGoodsForecastMakeBomUse::getDayNum97, obj.getDayNum97())
          .eq(Objects.nonNull(obj.getDayNum98()), ApsGoodsForecastMakeBomUse::getDayNum98, obj.getDayNum98())
          .eq(Objects.nonNull(obj.getDayNum99()), ApsGoodsForecastMakeBomUse::getDayNum99, obj.getDayNum99())
          .eq(Objects.nonNull(obj.getDayNum100()), ApsGoodsForecastMakeBomUse::getDayNum100, obj.getDayNum100())
          .eq(Objects.nonNull(obj.getDayNum101()), ApsGoodsForecastMakeBomUse::getDayNum101, obj.getDayNum101())
          .eq(Objects.nonNull(obj.getDayNum102()), ApsGoodsForecastMakeBomUse::getDayNum102, obj.getDayNum102())
          .eq(Objects.nonNull(obj.getDayNum103()), ApsGoodsForecastMakeBomUse::getDayNum103, obj.getDayNum103())
          .eq(Objects.nonNull(obj.getDayNum104()), ApsGoodsForecastMakeBomUse::getDayNum104, obj.getDayNum104())
          .eq(Objects.nonNull(obj.getDayNum105()), ApsGoodsForecastMakeBomUse::getDayNum105, obj.getDayNum105())
          .eq(Objects.nonNull(obj.getDayNum106()), ApsGoodsForecastMakeBomUse::getDayNum106, obj.getDayNum106())
          .eq(Objects.nonNull(obj.getDayNum107()), ApsGoodsForecastMakeBomUse::getDayNum107, obj.getDayNum107())
          .eq(Objects.nonNull(obj.getDayNum108()), ApsGoodsForecastMakeBomUse::getDayNum108, obj.getDayNum108())
          .eq(Objects.nonNull(obj.getDayNum109()), ApsGoodsForecastMakeBomUse::getDayNum109, obj.getDayNum109())
          .eq(Objects.nonNull(obj.getDayNum110()), ApsGoodsForecastMakeBomUse::getDayNum110, obj.getDayNum110())
          .eq(Objects.nonNull(obj.getDayNum111()), ApsGoodsForecastMakeBomUse::getDayNum111, obj.getDayNum111())
          .eq(Objects.nonNull(obj.getDayNum112()), ApsGoodsForecastMakeBomUse::getDayNum112, obj.getDayNum112())
          .eq(Objects.nonNull(obj.getDayNum113()), ApsGoodsForecastMakeBomUse::getDayNum113, obj.getDayNum113())
          .eq(Objects.nonNull(obj.getDayNum114()), ApsGoodsForecastMakeBomUse::getDayNum114, obj.getDayNum114())
          .eq(Objects.nonNull(obj.getDayNum115()), ApsGoodsForecastMakeBomUse::getDayNum115, obj.getDayNum115())
          .eq(Objects.nonNull(obj.getDayNum116()), ApsGoodsForecastMakeBomUse::getDayNum116, obj.getDayNum116())
          .eq(Objects.nonNull(obj.getDayNum117()), ApsGoodsForecastMakeBomUse::getDayNum117, obj.getDayNum117())
          .eq(Objects.nonNull(obj.getDayNum118()), ApsGoodsForecastMakeBomUse::getDayNum118, obj.getDayNum118())
          .eq(Objects.nonNull(obj.getDayNum119()), ApsGoodsForecastMakeBomUse::getDayNum119, obj.getDayNum119())
          .eq(Objects.nonNull(obj.getDayNum120()), ApsGoodsForecastMakeBomUse::getDayNum120, obj.getDayNum120())
          .eq(Objects.nonNull(obj.getDayNum121()), ApsGoodsForecastMakeBomUse::getDayNum121, obj.getDayNum121())
          .eq(Objects.nonNull(obj.getDayNum122()), ApsGoodsForecastMakeBomUse::getDayNum122, obj.getDayNum122())
          .eq(Objects.nonNull(obj.getDayNum123()), ApsGoodsForecastMakeBomUse::getDayNum123, obj.getDayNum123())
          .eq(Objects.nonNull(obj.getDayNum124()), ApsGoodsForecastMakeBomUse::getDayNum124, obj.getDayNum124())
          .eq(Objects.nonNull(obj.getDayNum125()), ApsGoodsForecastMakeBomUse::getDayNum125, obj.getDayNum125())
          .eq(Objects.nonNull(obj.getDayNum126()), ApsGoodsForecastMakeBomUse::getDayNum126, obj.getDayNum126())
          .eq(Objects.nonNull(obj.getDayNum127()), ApsGoodsForecastMakeBomUse::getDayNum127, obj.getDayNum127())
          .eq(Objects.nonNull(obj.getDayNum128()), ApsGoodsForecastMakeBomUse::getDayNum128, obj.getDayNum128())
          .eq(Objects.nonNull(obj.getDayNum129()), ApsGoodsForecastMakeBomUse::getDayNum129, obj.getDayNum129())
          .eq(Objects.nonNull(obj.getDayNum130()), ApsGoodsForecastMakeBomUse::getDayNum130, obj.getDayNum130())
          .eq(Objects.nonNull(obj.getDayNum131()), ApsGoodsForecastMakeBomUse::getDayNum131, obj.getDayNum131())
          .eq(Objects.nonNull(obj.getDayNum132()), ApsGoodsForecastMakeBomUse::getDayNum132, obj.getDayNum132())
          .eq(Objects.nonNull(obj.getDayNum133()), ApsGoodsForecastMakeBomUse::getDayNum133, obj.getDayNum133())
          .eq(Objects.nonNull(obj.getDayNum134()), ApsGoodsForecastMakeBomUse::getDayNum134, obj.getDayNum134())
          .eq(Objects.nonNull(obj.getDayNum135()), ApsGoodsForecastMakeBomUse::getDayNum135, obj.getDayNum135())
          .eq(Objects.nonNull(obj.getDayNum136()), ApsGoodsForecastMakeBomUse::getDayNum136, obj.getDayNum136())
          .eq(Objects.nonNull(obj.getDayNum137()), ApsGoodsForecastMakeBomUse::getDayNum137, obj.getDayNum137())
          .eq(Objects.nonNull(obj.getDayNum138()), ApsGoodsForecastMakeBomUse::getDayNum138, obj.getDayNum138())
          .eq(Objects.nonNull(obj.getDayNum139()), ApsGoodsForecastMakeBomUse::getDayNum139, obj.getDayNum139())
          .eq(Objects.nonNull(obj.getDayNum140()), ApsGoodsForecastMakeBomUse::getDayNum140, obj.getDayNum140())
          .eq(Objects.nonNull(obj.getDayNum141()), ApsGoodsForecastMakeBomUse::getDayNum141, obj.getDayNum141())
          .eq(Objects.nonNull(obj.getDayNum142()), ApsGoodsForecastMakeBomUse::getDayNum142, obj.getDayNum142())
          .eq(Objects.nonNull(obj.getDayNum143()), ApsGoodsForecastMakeBomUse::getDayNum143, obj.getDayNum143())
          .eq(Objects.nonNull(obj.getDayNum144()), ApsGoodsForecastMakeBomUse::getDayNum144, obj.getDayNum144())
          .eq(Objects.nonNull(obj.getDayNum145()), ApsGoodsForecastMakeBomUse::getDayNum145, obj.getDayNum145())
          .eq(Objects.nonNull(obj.getDayNum146()), ApsGoodsForecastMakeBomUse::getDayNum146, obj.getDayNum146())
          .eq(Objects.nonNull(obj.getDayNum147()), ApsGoodsForecastMakeBomUse::getDayNum147, obj.getDayNum147())
          .eq(Objects.nonNull(obj.getDayNum148()), ApsGoodsForecastMakeBomUse::getDayNum148, obj.getDayNum148())
          .eq(Objects.nonNull(obj.getDayNum149()), ApsGoodsForecastMakeBomUse::getDayNum149, obj.getDayNum149())
          .eq(Objects.nonNull(obj.getDayNum150()), ApsGoodsForecastMakeBomUse::getDayNum150, obj.getDayNum150())
          .eq(Objects.nonNull(obj.getDayNum151()), ApsGoodsForecastMakeBomUse::getDayNum151, obj.getDayNum151())
          .eq(Objects.nonNull(obj.getDayNum152()), ApsGoodsForecastMakeBomUse::getDayNum152, obj.getDayNum152())
          .eq(Objects.nonNull(obj.getDayNum153()), ApsGoodsForecastMakeBomUse::getDayNum153, obj.getDayNum153())
          .eq(Objects.nonNull(obj.getDayNum154()), ApsGoodsForecastMakeBomUse::getDayNum154, obj.getDayNum154())
          .eq(Objects.nonNull(obj.getDayNum155()), ApsGoodsForecastMakeBomUse::getDayNum155, obj.getDayNum155())
          .eq(Objects.nonNull(obj.getDayNum156()), ApsGoodsForecastMakeBomUse::getDayNum156, obj.getDayNum156())
          .eq(Objects.nonNull(obj.getDayNum157()), ApsGoodsForecastMakeBomUse::getDayNum157, obj.getDayNum157())
          .eq(Objects.nonNull(obj.getDayNum158()), ApsGoodsForecastMakeBomUse::getDayNum158, obj.getDayNum158())
          .eq(Objects.nonNull(obj.getDayNum159()), ApsGoodsForecastMakeBomUse::getDayNum159, obj.getDayNum159())
          .eq(Objects.nonNull(obj.getDayNum160()), ApsGoodsForecastMakeBomUse::getDayNum160, obj.getDayNum160())
          .eq(Objects.nonNull(obj.getDayNum161()), ApsGoodsForecastMakeBomUse::getDayNum161, obj.getDayNum161())
          .eq(Objects.nonNull(obj.getDayNum162()), ApsGoodsForecastMakeBomUse::getDayNum162, obj.getDayNum162())
          .eq(Objects.nonNull(obj.getDayNum163()), ApsGoodsForecastMakeBomUse::getDayNum163, obj.getDayNum163())
          .eq(Objects.nonNull(obj.getDayNum164()), ApsGoodsForecastMakeBomUse::getDayNum164, obj.getDayNum164())
          .eq(Objects.nonNull(obj.getDayNum165()), ApsGoodsForecastMakeBomUse::getDayNum165, obj.getDayNum165())
          .eq(Objects.nonNull(obj.getDayNum166()), ApsGoodsForecastMakeBomUse::getDayNum166, obj.getDayNum166())
          .eq(Objects.nonNull(obj.getDayNum167()), ApsGoodsForecastMakeBomUse::getDayNum167, obj.getDayNum167())
          .eq(Objects.nonNull(obj.getDayNum168()), ApsGoodsForecastMakeBomUse::getDayNum168, obj.getDayNum168())
          .eq(Objects.nonNull(obj.getDayNum169()), ApsGoodsForecastMakeBomUse::getDayNum169, obj.getDayNum169())
          .eq(Objects.nonNull(obj.getDayNum170()), ApsGoodsForecastMakeBomUse::getDayNum170, obj.getDayNum170())
          .eq(Objects.nonNull(obj.getDayNum171()), ApsGoodsForecastMakeBomUse::getDayNum171, obj.getDayNum171())
          .eq(Objects.nonNull(obj.getDayNum172()), ApsGoodsForecastMakeBomUse::getDayNum172, obj.getDayNum172())
          .eq(Objects.nonNull(obj.getDayNum173()), ApsGoodsForecastMakeBomUse::getDayNum173, obj.getDayNum173())
          .eq(Objects.nonNull(obj.getDayNum174()), ApsGoodsForecastMakeBomUse::getDayNum174, obj.getDayNum174())
          .eq(Objects.nonNull(obj.getDayNum175()), ApsGoodsForecastMakeBomUse::getDayNum175, obj.getDayNum175())
          .eq(Objects.nonNull(obj.getDayNum176()), ApsGoodsForecastMakeBomUse::getDayNum176, obj.getDayNum176())
          .eq(Objects.nonNull(obj.getDayNum177()), ApsGoodsForecastMakeBomUse::getDayNum177, obj.getDayNum177())
          .eq(Objects.nonNull(obj.getDayNum178()), ApsGoodsForecastMakeBomUse::getDayNum178, obj.getDayNum178())
          .eq(Objects.nonNull(obj.getDayNum179()), ApsGoodsForecastMakeBomUse::getDayNum179, obj.getDayNum179())
          .eq(Objects.nonNull(obj.getDayNum180()), ApsGoodsForecastMakeBomUse::getDayNum180, obj.getDayNum180())
          .eq(Objects.nonNull(obj.getDayNum181()), ApsGoodsForecastMakeBomUse::getDayNum181, obj.getDayNum181())
          .eq(Objects.nonNull(obj.getDayNum182()), ApsGoodsForecastMakeBomUse::getDayNum182, obj.getDayNum182())
          .eq(Objects.nonNull(obj.getDayNum183()), ApsGoodsForecastMakeBomUse::getDayNum183, obj.getDayNum183())
          .eq(Objects.nonNull(obj.getDayNum184()), ApsGoodsForecastMakeBomUse::getDayNum184, obj.getDayNum184())
          .eq(Objects.nonNull(obj.getDayNum185()), ApsGoodsForecastMakeBomUse::getDayNum185, obj.getDayNum185())
          .eq(Objects.nonNull(obj.getDayNum186()), ApsGoodsForecastMakeBomUse::getDayNum186, obj.getDayNum186())
          .eq(Objects.nonNull(obj.getDayNum187()), ApsGoodsForecastMakeBomUse::getDayNum187, obj.getDayNum187())
          .eq(Objects.nonNull(obj.getDayNum188()), ApsGoodsForecastMakeBomUse::getDayNum188, obj.getDayNum188())
          .eq(Objects.nonNull(obj.getDayNum189()), ApsGoodsForecastMakeBomUse::getDayNum189, obj.getDayNum189())
          .eq(Objects.nonNull(obj.getDayNum190()), ApsGoodsForecastMakeBomUse::getDayNum190, obj.getDayNum190())
          .eq(Objects.nonNull(obj.getDayNum191()), ApsGoodsForecastMakeBomUse::getDayNum191, obj.getDayNum191())
          .eq(Objects.nonNull(obj.getDayNum192()), ApsGoodsForecastMakeBomUse::getDayNum192, obj.getDayNum192())
          .eq(Objects.nonNull(obj.getDayNum193()), ApsGoodsForecastMakeBomUse::getDayNum193, obj.getDayNum193())
          .eq(Objects.nonNull(obj.getDayNum194()), ApsGoodsForecastMakeBomUse::getDayNum194, obj.getDayNum194())
          .eq(Objects.nonNull(obj.getDayNum195()), ApsGoodsForecastMakeBomUse::getDayNum195, obj.getDayNum195())
          .eq(Objects.nonNull(obj.getDayNum196()), ApsGoodsForecastMakeBomUse::getDayNum196, obj.getDayNum196())
          .eq(Objects.nonNull(obj.getDayNum197()), ApsGoodsForecastMakeBomUse::getDayNum197, obj.getDayNum197())
          .eq(Objects.nonNull(obj.getDayNum198()), ApsGoodsForecastMakeBomUse::getDayNum198, obj.getDayNum198())
          .eq(Objects.nonNull(obj.getDayNum199()), ApsGoodsForecastMakeBomUse::getDayNum199, obj.getDayNum199())
          .eq(Objects.nonNull(obj.getDayNum200()), ApsGoodsForecastMakeBomUse::getDayNum200, obj.getDayNum200())
          .eq(Objects.nonNull(obj.getDayNum201()), ApsGoodsForecastMakeBomUse::getDayNum201, obj.getDayNum201())
          .eq(Objects.nonNull(obj.getDayNum202()), ApsGoodsForecastMakeBomUse::getDayNum202, obj.getDayNum202())
          .eq(Objects.nonNull(obj.getDayNum203()), ApsGoodsForecastMakeBomUse::getDayNum203, obj.getDayNum203())
          .eq(Objects.nonNull(obj.getDayNum204()), ApsGoodsForecastMakeBomUse::getDayNum204, obj.getDayNum204())
          .eq(Objects.nonNull(obj.getDayNum205()), ApsGoodsForecastMakeBomUse::getDayNum205, obj.getDayNum205())
          .eq(Objects.nonNull(obj.getDayNum206()), ApsGoodsForecastMakeBomUse::getDayNum206, obj.getDayNum206())
          .eq(Objects.nonNull(obj.getDayNum207()), ApsGoodsForecastMakeBomUse::getDayNum207, obj.getDayNum207())
          .eq(Objects.nonNull(obj.getDayNum208()), ApsGoodsForecastMakeBomUse::getDayNum208, obj.getDayNum208())
          .eq(Objects.nonNull(obj.getDayNum209()), ApsGoodsForecastMakeBomUse::getDayNum209, obj.getDayNum209())
          .eq(Objects.nonNull(obj.getDayNum210()), ApsGoodsForecastMakeBomUse::getDayNum210, obj.getDayNum210())
          .eq(Objects.nonNull(obj.getDayNum211()), ApsGoodsForecastMakeBomUse::getDayNum211, obj.getDayNum211())
          .eq(Objects.nonNull(obj.getDayNum212()), ApsGoodsForecastMakeBomUse::getDayNum212, obj.getDayNum212())
          .eq(Objects.nonNull(obj.getDayNum213()), ApsGoodsForecastMakeBomUse::getDayNum213, obj.getDayNum213())
          .eq(Objects.nonNull(obj.getDayNum214()), ApsGoodsForecastMakeBomUse::getDayNum214, obj.getDayNum214())
          .eq(Objects.nonNull(obj.getDayNum215()), ApsGoodsForecastMakeBomUse::getDayNum215, obj.getDayNum215())
          .eq(Objects.nonNull(obj.getDayNum216()), ApsGoodsForecastMakeBomUse::getDayNum216, obj.getDayNum216())
          .eq(Objects.nonNull(obj.getDayNum217()), ApsGoodsForecastMakeBomUse::getDayNum217, obj.getDayNum217())
          .eq(Objects.nonNull(obj.getDayNum218()), ApsGoodsForecastMakeBomUse::getDayNum218, obj.getDayNum218())
          .eq(Objects.nonNull(obj.getDayNum219()), ApsGoodsForecastMakeBomUse::getDayNum219, obj.getDayNum219())
          .eq(Objects.nonNull(obj.getDayNum220()), ApsGoodsForecastMakeBomUse::getDayNum220, obj.getDayNum220())
          .eq(Objects.nonNull(obj.getDayNum221()), ApsGoodsForecastMakeBomUse::getDayNum221, obj.getDayNum221())
          .eq(Objects.nonNull(obj.getDayNum222()), ApsGoodsForecastMakeBomUse::getDayNum222, obj.getDayNum222())
          .eq(Objects.nonNull(obj.getDayNum223()), ApsGoodsForecastMakeBomUse::getDayNum223, obj.getDayNum223())
          .eq(Objects.nonNull(obj.getDayNum224()), ApsGoodsForecastMakeBomUse::getDayNum224, obj.getDayNum224())
          .eq(Objects.nonNull(obj.getDayNum225()), ApsGoodsForecastMakeBomUse::getDayNum225, obj.getDayNum225())
          .eq(Objects.nonNull(obj.getDayNum226()), ApsGoodsForecastMakeBomUse::getDayNum226, obj.getDayNum226())
          .eq(Objects.nonNull(obj.getDayNum227()), ApsGoodsForecastMakeBomUse::getDayNum227, obj.getDayNum227())
          .eq(Objects.nonNull(obj.getDayNum228()), ApsGoodsForecastMakeBomUse::getDayNum228, obj.getDayNum228())
          .eq(Objects.nonNull(obj.getDayNum229()), ApsGoodsForecastMakeBomUse::getDayNum229, obj.getDayNum229())
          .eq(Objects.nonNull(obj.getDayNum230()), ApsGoodsForecastMakeBomUse::getDayNum230, obj.getDayNum230())
          .eq(Objects.nonNull(obj.getDayNum231()), ApsGoodsForecastMakeBomUse::getDayNum231, obj.getDayNum231())
          .eq(Objects.nonNull(obj.getDayNum232()), ApsGoodsForecastMakeBomUse::getDayNum232, obj.getDayNum232())
          .eq(Objects.nonNull(obj.getDayNum233()), ApsGoodsForecastMakeBomUse::getDayNum233, obj.getDayNum233())
          .eq(Objects.nonNull(obj.getDayNum234()), ApsGoodsForecastMakeBomUse::getDayNum234, obj.getDayNum234())
          .eq(Objects.nonNull(obj.getDayNum235()), ApsGoodsForecastMakeBomUse::getDayNum235, obj.getDayNum235())
          .eq(Objects.nonNull(obj.getDayNum236()), ApsGoodsForecastMakeBomUse::getDayNum236, obj.getDayNum236())
          .eq(Objects.nonNull(obj.getDayNum237()), ApsGoodsForecastMakeBomUse::getDayNum237, obj.getDayNum237())
          .eq(Objects.nonNull(obj.getDayNum238()), ApsGoodsForecastMakeBomUse::getDayNum238, obj.getDayNum238())
          .eq(Objects.nonNull(obj.getDayNum239()), ApsGoodsForecastMakeBomUse::getDayNum239, obj.getDayNum239())
          .eq(Objects.nonNull(obj.getDayNum240()), ApsGoodsForecastMakeBomUse::getDayNum240, obj.getDayNum240())
          .eq(Objects.nonNull(obj.getDayNum241()), ApsGoodsForecastMakeBomUse::getDayNum241, obj.getDayNum241())
          .eq(Objects.nonNull(obj.getDayNum242()), ApsGoodsForecastMakeBomUse::getDayNum242, obj.getDayNum242())
          .eq(Objects.nonNull(obj.getDayNum243()), ApsGoodsForecastMakeBomUse::getDayNum243, obj.getDayNum243())
          .eq(Objects.nonNull(obj.getDayNum244()), ApsGoodsForecastMakeBomUse::getDayNum244, obj.getDayNum244())
          .eq(Objects.nonNull(obj.getDayNum245()), ApsGoodsForecastMakeBomUse::getDayNum245, obj.getDayNum245())
          .eq(Objects.nonNull(obj.getDayNum246()), ApsGoodsForecastMakeBomUse::getDayNum246, obj.getDayNum246())
          .eq(Objects.nonNull(obj.getDayNum247()), ApsGoodsForecastMakeBomUse::getDayNum247, obj.getDayNum247())
          .eq(Objects.nonNull(obj.getDayNum248()), ApsGoodsForecastMakeBomUse::getDayNum248, obj.getDayNum248())
          .eq(Objects.nonNull(obj.getDayNum249()), ApsGoodsForecastMakeBomUse::getDayNum249, obj.getDayNum249())
          .eq(Objects.nonNull(obj.getDayNum250()), ApsGoodsForecastMakeBomUse::getDayNum250, obj.getDayNum250())
          .eq(Objects.nonNull(obj.getDayNum251()), ApsGoodsForecastMakeBomUse::getDayNum251, obj.getDayNum251())
          .eq(Objects.nonNull(obj.getDayNum252()), ApsGoodsForecastMakeBomUse::getDayNum252, obj.getDayNum252())
          .eq(Objects.nonNull(obj.getDayNum253()), ApsGoodsForecastMakeBomUse::getDayNum253, obj.getDayNum253())
          .eq(Objects.nonNull(obj.getDayNum254()), ApsGoodsForecastMakeBomUse::getDayNum254, obj.getDayNum254())
          .eq(Objects.nonNull(obj.getDayNum255()), ApsGoodsForecastMakeBomUse::getDayNum255, obj.getDayNum255())
          .eq(Objects.nonNull(obj.getDayNum256()), ApsGoodsForecastMakeBomUse::getDayNum256, obj.getDayNum256())
          .eq(Objects.nonNull(obj.getDayNum257()), ApsGoodsForecastMakeBomUse::getDayNum257, obj.getDayNum257())
          .eq(Objects.nonNull(obj.getDayNum258()), ApsGoodsForecastMakeBomUse::getDayNum258, obj.getDayNum258())
          .eq(Objects.nonNull(obj.getDayNum259()), ApsGoodsForecastMakeBomUse::getDayNum259, obj.getDayNum259())
          .eq(Objects.nonNull(obj.getDayNum260()), ApsGoodsForecastMakeBomUse::getDayNum260, obj.getDayNum260())
          .eq(Objects.nonNull(obj.getDayNum261()), ApsGoodsForecastMakeBomUse::getDayNum261, obj.getDayNum261())
          .eq(Objects.nonNull(obj.getDayNum262()), ApsGoodsForecastMakeBomUse::getDayNum262, obj.getDayNum262())
          .eq(Objects.nonNull(obj.getDayNum263()), ApsGoodsForecastMakeBomUse::getDayNum263, obj.getDayNum263())
          .eq(Objects.nonNull(obj.getDayNum264()), ApsGoodsForecastMakeBomUse::getDayNum264, obj.getDayNum264())
          .eq(Objects.nonNull(obj.getDayNum265()), ApsGoodsForecastMakeBomUse::getDayNum265, obj.getDayNum265())
          .eq(Objects.nonNull(obj.getDayNum266()), ApsGoodsForecastMakeBomUse::getDayNum266, obj.getDayNum266())
          .eq(Objects.nonNull(obj.getDayNum267()), ApsGoodsForecastMakeBomUse::getDayNum267, obj.getDayNum267())
          .eq(Objects.nonNull(obj.getDayNum268()), ApsGoodsForecastMakeBomUse::getDayNum268, obj.getDayNum268())
          .eq(Objects.nonNull(obj.getDayNum269()), ApsGoodsForecastMakeBomUse::getDayNum269, obj.getDayNum269())
          .eq(Objects.nonNull(obj.getDayNum270()), ApsGoodsForecastMakeBomUse::getDayNum270, obj.getDayNum270())
          .eq(Objects.nonNull(obj.getDayNum271()), ApsGoodsForecastMakeBomUse::getDayNum271, obj.getDayNum271())
          .eq(Objects.nonNull(obj.getDayNum272()), ApsGoodsForecastMakeBomUse::getDayNum272, obj.getDayNum272())
          .eq(Objects.nonNull(obj.getDayNum273()), ApsGoodsForecastMakeBomUse::getDayNum273, obj.getDayNum273())
          .eq(Objects.nonNull(obj.getDayNum274()), ApsGoodsForecastMakeBomUse::getDayNum274, obj.getDayNum274())
          .eq(Objects.nonNull(obj.getDayNum275()), ApsGoodsForecastMakeBomUse::getDayNum275, obj.getDayNum275())
          .eq(Objects.nonNull(obj.getDayNum276()), ApsGoodsForecastMakeBomUse::getDayNum276, obj.getDayNum276())
          .eq(Objects.nonNull(obj.getDayNum277()), ApsGoodsForecastMakeBomUse::getDayNum277, obj.getDayNum277())
          .eq(Objects.nonNull(obj.getDayNum278()), ApsGoodsForecastMakeBomUse::getDayNum278, obj.getDayNum278())
          .eq(Objects.nonNull(obj.getDayNum279()), ApsGoodsForecastMakeBomUse::getDayNum279, obj.getDayNum279())
          .eq(Objects.nonNull(obj.getDayNum280()), ApsGoodsForecastMakeBomUse::getDayNum280, obj.getDayNum280())
          .eq(Objects.nonNull(obj.getDayNum281()), ApsGoodsForecastMakeBomUse::getDayNum281, obj.getDayNum281())
          .eq(Objects.nonNull(obj.getDayNum282()), ApsGoodsForecastMakeBomUse::getDayNum282, obj.getDayNum282())
          .eq(Objects.nonNull(obj.getDayNum283()), ApsGoodsForecastMakeBomUse::getDayNum283, obj.getDayNum283())
          .eq(Objects.nonNull(obj.getDayNum284()), ApsGoodsForecastMakeBomUse::getDayNum284, obj.getDayNum284())
          .eq(Objects.nonNull(obj.getDayNum285()), ApsGoodsForecastMakeBomUse::getDayNum285, obj.getDayNum285())
          .eq(Objects.nonNull(obj.getDayNum286()), ApsGoodsForecastMakeBomUse::getDayNum286, obj.getDayNum286())
          .eq(Objects.nonNull(obj.getDayNum287()), ApsGoodsForecastMakeBomUse::getDayNum287, obj.getDayNum287())
          .eq(Objects.nonNull(obj.getDayNum288()), ApsGoodsForecastMakeBomUse::getDayNum288, obj.getDayNum288())
          .eq(Objects.nonNull(obj.getDayNum289()), ApsGoodsForecastMakeBomUse::getDayNum289, obj.getDayNum289())
          .eq(Objects.nonNull(obj.getDayNum290()), ApsGoodsForecastMakeBomUse::getDayNum290, obj.getDayNum290())
          .eq(Objects.nonNull(obj.getDayNum291()), ApsGoodsForecastMakeBomUse::getDayNum291, obj.getDayNum291())
          .eq(Objects.nonNull(obj.getDayNum292()), ApsGoodsForecastMakeBomUse::getDayNum292, obj.getDayNum292())
          .eq(Objects.nonNull(obj.getDayNum293()), ApsGoodsForecastMakeBomUse::getDayNum293, obj.getDayNum293())
          .eq(Objects.nonNull(obj.getDayNum294()), ApsGoodsForecastMakeBomUse::getDayNum294, obj.getDayNum294())
          .eq(Objects.nonNull(obj.getDayNum295()), ApsGoodsForecastMakeBomUse::getDayNum295, obj.getDayNum295())
          .eq(Objects.nonNull(obj.getDayNum296()), ApsGoodsForecastMakeBomUse::getDayNum296, obj.getDayNum296())
          .eq(Objects.nonNull(obj.getDayNum297()), ApsGoodsForecastMakeBomUse::getDayNum297, obj.getDayNum297())
          .eq(Objects.nonNull(obj.getDayNum298()), ApsGoodsForecastMakeBomUse::getDayNum298, obj.getDayNum298())
          .eq(Objects.nonNull(obj.getDayNum299()), ApsGoodsForecastMakeBomUse::getDayNum299, obj.getDayNum299())
          .eq(Objects.nonNull(obj.getDayNum300()), ApsGoodsForecastMakeBomUse::getDayNum300, obj.getDayNum300())
          .eq(Objects.nonNull(obj.getDayNum301()), ApsGoodsForecastMakeBomUse::getDayNum301, obj.getDayNum301())
          .eq(Objects.nonNull(obj.getDayNum302()), ApsGoodsForecastMakeBomUse::getDayNum302, obj.getDayNum302())
          .eq(Objects.nonNull(obj.getDayNum303()), ApsGoodsForecastMakeBomUse::getDayNum303, obj.getDayNum303())
          .eq(Objects.nonNull(obj.getDayNum304()), ApsGoodsForecastMakeBomUse::getDayNum304, obj.getDayNum304())
          .eq(Objects.nonNull(obj.getDayNum305()), ApsGoodsForecastMakeBomUse::getDayNum305, obj.getDayNum305())
          .eq(Objects.nonNull(obj.getDayNum306()), ApsGoodsForecastMakeBomUse::getDayNum306, obj.getDayNum306())
          .eq(Objects.nonNull(obj.getDayNum307()), ApsGoodsForecastMakeBomUse::getDayNum307, obj.getDayNum307())
          .eq(Objects.nonNull(obj.getDayNum308()), ApsGoodsForecastMakeBomUse::getDayNum308, obj.getDayNum308())
          .eq(Objects.nonNull(obj.getDayNum309()), ApsGoodsForecastMakeBomUse::getDayNum309, obj.getDayNum309())
          .eq(Objects.nonNull(obj.getDayNum310()), ApsGoodsForecastMakeBomUse::getDayNum310, obj.getDayNum310())
          .eq(Objects.nonNull(obj.getDayNum311()), ApsGoodsForecastMakeBomUse::getDayNum311, obj.getDayNum311())
          .eq(Objects.nonNull(obj.getDayNum312()), ApsGoodsForecastMakeBomUse::getDayNum312, obj.getDayNum312())
          .eq(Objects.nonNull(obj.getDayNum313()), ApsGoodsForecastMakeBomUse::getDayNum313, obj.getDayNum313())
          .eq(Objects.nonNull(obj.getDayNum314()), ApsGoodsForecastMakeBomUse::getDayNum314, obj.getDayNum314())
          .eq(Objects.nonNull(obj.getDayNum315()), ApsGoodsForecastMakeBomUse::getDayNum315, obj.getDayNum315())
          .eq(Objects.nonNull(obj.getDayNum316()), ApsGoodsForecastMakeBomUse::getDayNum316, obj.getDayNum316())
          .eq(Objects.nonNull(obj.getDayNum317()), ApsGoodsForecastMakeBomUse::getDayNum317, obj.getDayNum317())
          .eq(Objects.nonNull(obj.getDayNum318()), ApsGoodsForecastMakeBomUse::getDayNum318, obj.getDayNum318())
          .eq(Objects.nonNull(obj.getDayNum319()), ApsGoodsForecastMakeBomUse::getDayNum319, obj.getDayNum319())
          .eq(Objects.nonNull(obj.getDayNum320()), ApsGoodsForecastMakeBomUse::getDayNum320, obj.getDayNum320())
          .eq(Objects.nonNull(obj.getDayNum321()), ApsGoodsForecastMakeBomUse::getDayNum321, obj.getDayNum321())
          .eq(Objects.nonNull(obj.getDayNum322()), ApsGoodsForecastMakeBomUse::getDayNum322, obj.getDayNum322())
          .eq(Objects.nonNull(obj.getDayNum323()), ApsGoodsForecastMakeBomUse::getDayNum323, obj.getDayNum323())
          .eq(Objects.nonNull(obj.getDayNum324()), ApsGoodsForecastMakeBomUse::getDayNum324, obj.getDayNum324())
          .eq(Objects.nonNull(obj.getDayNum325()), ApsGoodsForecastMakeBomUse::getDayNum325, obj.getDayNum325())
          .eq(Objects.nonNull(obj.getDayNum326()), ApsGoodsForecastMakeBomUse::getDayNum326, obj.getDayNum326())
          .eq(Objects.nonNull(obj.getDayNum327()), ApsGoodsForecastMakeBomUse::getDayNum327, obj.getDayNum327())
          .eq(Objects.nonNull(obj.getDayNum328()), ApsGoodsForecastMakeBomUse::getDayNum328, obj.getDayNum328())
          .eq(Objects.nonNull(obj.getDayNum329()), ApsGoodsForecastMakeBomUse::getDayNum329, obj.getDayNum329())
          .eq(Objects.nonNull(obj.getDayNum330()), ApsGoodsForecastMakeBomUse::getDayNum330, obj.getDayNum330())
          .eq(Objects.nonNull(obj.getDayNum331()), ApsGoodsForecastMakeBomUse::getDayNum331, obj.getDayNum331())
          .eq(Objects.nonNull(obj.getDayNum332()), ApsGoodsForecastMakeBomUse::getDayNum332, obj.getDayNum332())
          .eq(Objects.nonNull(obj.getDayNum333()), ApsGoodsForecastMakeBomUse::getDayNum333, obj.getDayNum333())
          .eq(Objects.nonNull(obj.getDayNum334()), ApsGoodsForecastMakeBomUse::getDayNum334, obj.getDayNum334())
          .eq(Objects.nonNull(obj.getDayNum335()), ApsGoodsForecastMakeBomUse::getDayNum335, obj.getDayNum335())
          .eq(Objects.nonNull(obj.getDayNum336()), ApsGoodsForecastMakeBomUse::getDayNum336, obj.getDayNum336())
          .eq(Objects.nonNull(obj.getDayNum337()), ApsGoodsForecastMakeBomUse::getDayNum337, obj.getDayNum337())
          .eq(Objects.nonNull(obj.getDayNum338()), ApsGoodsForecastMakeBomUse::getDayNum338, obj.getDayNum338())
          .eq(Objects.nonNull(obj.getDayNum339()), ApsGoodsForecastMakeBomUse::getDayNum339, obj.getDayNum339())
          .eq(Objects.nonNull(obj.getDayNum340()), ApsGoodsForecastMakeBomUse::getDayNum340, obj.getDayNum340())
          .eq(Objects.nonNull(obj.getDayNum341()), ApsGoodsForecastMakeBomUse::getDayNum341, obj.getDayNum341())
          .eq(Objects.nonNull(obj.getDayNum342()), ApsGoodsForecastMakeBomUse::getDayNum342, obj.getDayNum342())
          .eq(Objects.nonNull(obj.getDayNum343()), ApsGoodsForecastMakeBomUse::getDayNum343, obj.getDayNum343())
          .eq(Objects.nonNull(obj.getDayNum344()), ApsGoodsForecastMakeBomUse::getDayNum344, obj.getDayNum344())
          .eq(Objects.nonNull(obj.getDayNum345()), ApsGoodsForecastMakeBomUse::getDayNum345, obj.getDayNum345())
          .eq(Objects.nonNull(obj.getDayNum346()), ApsGoodsForecastMakeBomUse::getDayNum346, obj.getDayNum346())
          .eq(Objects.nonNull(obj.getDayNum347()), ApsGoodsForecastMakeBomUse::getDayNum347, obj.getDayNum347())
          .eq(Objects.nonNull(obj.getDayNum348()), ApsGoodsForecastMakeBomUse::getDayNum348, obj.getDayNum348())
          .eq(Objects.nonNull(obj.getDayNum349()), ApsGoodsForecastMakeBomUse::getDayNum349, obj.getDayNum349())
          .eq(Objects.nonNull(obj.getDayNum350()), ApsGoodsForecastMakeBomUse::getDayNum350, obj.getDayNum350())
          .eq(Objects.nonNull(obj.getDayNum351()), ApsGoodsForecastMakeBomUse::getDayNum351, obj.getDayNum351())
          .eq(Objects.nonNull(obj.getDayNum352()), ApsGoodsForecastMakeBomUse::getDayNum352, obj.getDayNum352())
          .eq(Objects.nonNull(obj.getDayNum353()), ApsGoodsForecastMakeBomUse::getDayNum353, obj.getDayNum353())
          .eq(Objects.nonNull(obj.getDayNum354()), ApsGoodsForecastMakeBomUse::getDayNum354, obj.getDayNum354())
          .eq(Objects.nonNull(obj.getDayNum355()), ApsGoodsForecastMakeBomUse::getDayNum355, obj.getDayNum355())
          .eq(Objects.nonNull(obj.getDayNum356()), ApsGoodsForecastMakeBomUse::getDayNum356, obj.getDayNum356())
          .eq(Objects.nonNull(obj.getDayNum357()), ApsGoodsForecastMakeBomUse::getDayNum357, obj.getDayNum357())
          .eq(Objects.nonNull(obj.getDayNum358()), ApsGoodsForecastMakeBomUse::getDayNum358, obj.getDayNum358())
          .eq(Objects.nonNull(obj.getDayNum359()), ApsGoodsForecastMakeBomUse::getDayNum359, obj.getDayNum359())
          .eq(Objects.nonNull(obj.getDayNum360()), ApsGoodsForecastMakeBomUse::getDayNum360, obj.getDayNum360())
          .eq(Objects.nonNull(obj.getDayNum361()), ApsGoodsForecastMakeBomUse::getDayNum361, obj.getDayNum361())
          .eq(Objects.nonNull(obj.getDayNum362()), ApsGoodsForecastMakeBomUse::getDayNum362, obj.getDayNum362())
          .eq(Objects.nonNull(obj.getDayNum363()), ApsGoodsForecastMakeBomUse::getDayNum363, obj.getDayNum363())
          .eq(Objects.nonNull(obj.getDayNum364()), ApsGoodsForecastMakeBomUse::getDayNum364, obj.getDayNum364())
          .eq(Objects.nonNull(obj.getDayNum365()), ApsGoodsForecastMakeBomUse::getDayNum365, obj.getDayNum365())
          .eq(Objects.nonNull(obj.getDayNum366()), ApsGoodsForecastMakeBomUse::getDayNum366, obj.getDayNum366())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMakeBomUse::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getMakeSaleConfigId()), ApsGoodsForecastMakeBomUse::getMakeSaleConfigId, obj.getMakeSaleConfigId())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMakeBomUse::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMakeBomUse> page) {

    ServiceComment.header(page, "ApsGoodsForecastMakeBomUseService#queryPageList");

  }


}

