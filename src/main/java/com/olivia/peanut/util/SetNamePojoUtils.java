package com.olivia.peanut.util;

import static com.olivia.sdk.utils.Str.FACTORY_NAME;

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
}
