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

  @Override
  public int compareTo(Integer o) {
    return Integer.compare(getValue(), o);
  }
}
