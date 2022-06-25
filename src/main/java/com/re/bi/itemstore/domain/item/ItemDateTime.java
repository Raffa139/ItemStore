package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractValueObject;

import java.time.LocalDateTime;

public class ItemDateTime extends AbstractValueObject<LocalDateTime> {
  public ItemDateTime(LocalDateTime value) {
    super(value);
  }

  @Override
  protected boolean valid(LocalDateTime value) {
    LocalDateTime today = LocalDateTime.now();
    return (value.isEqual(today) || value.isBefore(today)) && super.valid(value);
  }
}
