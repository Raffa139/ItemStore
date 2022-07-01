package com.re.bi.itemstore.application.search;

import com.re.bi.itemstore.domain.item.ItemValue;

public class ItemValueSearchCriteria {
  private String key;

  private String operation;

  private ItemValue value;

  public ItemValueSearchCriteria(String key, String operation, ItemValue value) {
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

  public ItemValue getValue() {
    return value;
  }
}
