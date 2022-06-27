package com.re.bi.itemstore.application.item;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.re.bi.itemstore.domain.item.ItemValue;

public class ItemValueConverter extends StdConverter<ItemValue, Integer> {
  @Override
  public Integer convert(ItemValue itemValue) {
    return itemValue.getValue();
  }
}
