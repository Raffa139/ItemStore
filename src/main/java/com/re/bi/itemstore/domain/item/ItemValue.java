package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class ItemValue extends AbstractValueObject<Integer> {
  protected ItemValue() {
  }

  public ItemValue(Integer value) {
    super(value);
  }
}
