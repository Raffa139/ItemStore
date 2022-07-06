package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractValueObject;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class ItemDateTime extends AbstractValueObject<LocalDateTime> {
  protected ItemDateTime() {
  }

  public ItemDateTime(LocalDateTime value) {
    super(value);
  }

  @Override
  public int compareTo(LocalDateTime o) {
    return getValue().compareTo(o);
  }

  @Override
  protected boolean valid(LocalDateTime value) {
    LocalDateTime today = LocalDateTime.now();
    return (value.isEqual(today) || value.isBefore(today)) && super.valid(value);
  }
}
