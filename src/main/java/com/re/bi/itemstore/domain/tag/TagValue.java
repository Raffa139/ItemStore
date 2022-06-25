package com.re.bi.itemstore.domain.tag;

import com.re.bi.itemstore.domain.AbstractValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class TagValue extends AbstractValueObject<String> {
  protected TagValue() {
  }

  public TagValue(String value) {
    super(value);
  }

  @Override
  protected boolean valid(String value) {
    return !value.isBlank() && super.valid(value);
  }
}
