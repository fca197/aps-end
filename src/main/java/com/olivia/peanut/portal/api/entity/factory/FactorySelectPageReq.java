package com.olivia.peanut.portal.api.entity.factory;

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
public class FactorySelectPageReq extends PageQuery<com.olivia.peanut.portal.model.Factory> {

}
