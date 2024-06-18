package com.olivia.peanut.portal.api.entity.brand;

import com.olivia.peanut.portal.model.Brand;
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
public class BrandReq extends PageQuery<Brand> {

}
