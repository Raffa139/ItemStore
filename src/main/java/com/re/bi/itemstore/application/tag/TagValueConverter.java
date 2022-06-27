package com.re.bi.itemstore.application.tag;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.re.bi.itemstore.domain.tag.TagValue;

public class TagValueConverter extends StdConverter<TagValue, String> {
  @Override
  public String convert(TagValue tagValue) {
    return tagValue.getValue();
  }
}
