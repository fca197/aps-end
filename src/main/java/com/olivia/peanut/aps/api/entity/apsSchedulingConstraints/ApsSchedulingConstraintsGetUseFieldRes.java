package com.olivia.peanut.aps.api.entity.apsSchedulingConstraints;

import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.FieldConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingConstraintsGetUseFieldRes {


  private List<FieldConfig> values;
//  private Map<String, ConstrainedField> constrainedFieldMap;
}
