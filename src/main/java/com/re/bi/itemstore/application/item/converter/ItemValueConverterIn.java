package com.re.bi.itemstore.application.item.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.re.bi.itemstore.domain.item.ItemValue;

public class ItemValueConverterIn extends StdConverter<Integer, ItemValue> {
  @Override
  public ItemValue convert(Integer integer) {
    return new ItemValue(integer);
  }
}
