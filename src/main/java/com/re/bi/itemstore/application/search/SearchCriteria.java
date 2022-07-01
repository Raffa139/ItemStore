package com.re.bi.itemstore.application.search;

import com.re.bi.itemstore.domain.AbstractValueObject;

public class SearchCriteria<V> {
  private String key;

  private String operation;

  private Object value;

  public SearchCriteria(String key, String operation, AbstractValueObject<V> value) {
    this.key = key;
    this.operation = operation;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getOperation() {
    return operation;
  }

  public Object getValue() {
    return value;
  }
}
