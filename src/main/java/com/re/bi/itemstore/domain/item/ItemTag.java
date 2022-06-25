package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractValueObject;

public class ItemTag extends AbstractValueObject<String> {
  public ItemTag(String value) {
    super(value);
  }

  @Override
  protected boolean valid(String value) {
    return !value.isBlank() && super.valid(value);
  }
}
