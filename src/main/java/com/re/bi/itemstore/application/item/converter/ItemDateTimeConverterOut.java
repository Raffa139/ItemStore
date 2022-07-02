package com.re.bi.itemstore.application.item.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.re.bi.itemstore.domain.item.ItemDateTime;

import java.time.format.DateTimeFormatter;

public class ItemDateTimeConverterOut extends StdConverter<ItemDateTime, String> {
  @Override
  public String convert(ItemDateTime itemDateTime) {
    return itemDateTime.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
