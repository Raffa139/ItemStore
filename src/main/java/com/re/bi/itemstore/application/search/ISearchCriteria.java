package com.re.bi.itemstore.application.search;

import com.re.bi.itemstore.domain.AbstractValueObject;

public class ISearchCriteria<V extends AbstractValueObject<?>> {
  protected String key;

  protected String operation;

  protected V value;

  public ISearchCriteria(String key, String operation, V value) {
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

  public V getValue() {
    return value;
  }
}
