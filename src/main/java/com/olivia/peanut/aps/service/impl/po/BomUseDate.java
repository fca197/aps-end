package com.olivia.peanut.aps.service.impl.po;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class BomUseDate {

  private Long bomId;
  private LocalDate currentDate;

  @Override
  public boolean equals(Object object) {

    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    BomUseDate bomUseDate = (BomUseDate) object;
    return Objects.equals(bomId, bomUseDate.bomId) && Objects.equals(currentDate, bomUseDate.currentDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bomId, currentDate);
  }
}
