package com.re.bi.itemstore.application.item;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.re.bi.itemstore.domain.item.ItemTags;

import java.util.List;

public class ItemTagsConverter extends StdConverter<ItemTags, List<String>> {
  @Override
  public List<String> convert(ItemTags itemTags) {
    return itemTags.toList();
  }
}
