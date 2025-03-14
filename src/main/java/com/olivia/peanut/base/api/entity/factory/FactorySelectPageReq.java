package com.olivia.peanut.base.api.entity.factory;

import com.olivia.peanut.base.model.Factory;
import com.olivia.sdk.controller.entity.PageQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class FactorySelectPageReq extends PageQuery<Factory> {

}
