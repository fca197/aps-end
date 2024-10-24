package com.olivia.peanut.util;

import static com.olivia.sdk.utils.Str.FACTORY_NAME;

import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.portal.service.FactoryService;
import com.olivia.peanut.portal.service.LoginAccountService;
import com.olivia.sdk.service.pojo.NameConfig;
import com.olivia.sdk.service.pojo.SetNamePojo;
import com.olivia.sdk.utils.Str;

import java.util.List;

public class SetNamePojoUtils {

  //factory
  public static final SetNamePojo FACTORY = new SetNamePojo()//
      .setNameFieldName(FACTORY_NAME).setServiceName(FactoryService.class) //
      .setNameConfigList(List.of(new NameConfig().setIdField(Str.FACTORY_ID).setNameField(FACTORY_NAME)));

  //  goods
  public static final SetNamePojo GOODS = new SetNamePojo()//
      .setNameFieldName("goodsName").setServiceName(ApsGoodsService.class) //
      .setNameConfigList(List.of(new NameConfig().setIdField("goodsId").setNameFieldList(List.of("goodsName"))));

  // loginUser
  public static final SetNamePojo OP_USER_NAME = new SetNamePojo()//
      .setNameFieldName("userName").setServiceName(LoginAccountService.class) //
      .setNameConfigList(List.of(new NameConfig().setIdField("createBy").setNameField("createUserName"),//
          new NameConfig().setIdField("updateBy").setNameField("updateUserName")));


  /****
   *  获取SetNamePojo 对象
   * @param beanClass  对象查询 service接口
   * @param nameFieldName service接口返回对象中名称对应的字段名称
   * @param idField  返回列表中 service接口 入参接口ID字段名称
   * @param nameField 返回列表中  id需要映射的字段名称
   * @return SetNamePojo 对象
   */
  public static SetNamePojo getSetNamePojo(Class<? extends IService<?>> beanClass, String nameFieldName, String idField, String nameField) {
    return new SetNamePojo().setServiceName(beanClass).setNameFieldName(nameFieldName).setNameConfigList(List.of(new NameConfig().setIdField(idField).setNameField(nameField)));
  }
}
