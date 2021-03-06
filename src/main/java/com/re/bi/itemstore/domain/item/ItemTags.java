package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractValueObject;

import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class ItemTags extends AbstractValueObject<String> {
  protected ItemTags() {
  }

  public ItemTags(String value) {
    super(value);
  }

  public ItemTags(Collection<String> tags) {
    super(String.join(";", tags));
  }

  public List<String> toList() {
    return Arrays.stream(getValue().split(";")).collect(Collectors.toList());
  }

  @Override
  public int compareTo(String o) {
    return getValue().compareTo(o);
  }

  @Override
  protected boolean valid(String value) {
    return !value.isBlank() && super.valid(value);
  }
}
