package com.re.bi.itemstore.application.item.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.re.bi.itemstore.domain.item.ItemTags;

import java.util.List;

public class ItemTagsConverterIn extends StdConverter<List<String>, ItemTags> {
  @Override
  public ItemTags convert(List<String> strings) {
    return new ItemTags(strings);
  }
}
