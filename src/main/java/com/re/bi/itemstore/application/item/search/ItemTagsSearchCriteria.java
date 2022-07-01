package com.re.bi.itemstore.application.item.search;

import com.re.bi.itemstore.domain.item.ItemTags;

import java.util.Objects;

public class ItemTagsSearchCriteria implements ItemSearchCriteria<ItemTags> {
  private String key;

  private String operation;

  private ItemTags value;

  public ItemTagsSearchCriteria(String key, String operation, ItemTags value) {
    this.key = key;
    this.operation = operation;
    this.value = value;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public String getOperation() {
    return operation;
  }

  @Override
  public ItemTags getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemTagsSearchCriteria that = (ItemTagsSearchCriteria) o;

    if (!Objects.equals(key, that.key)) return false;
    if (!Objects.equals(operation, that.operation)) return false;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (operation != null ? operation.hashCode() : 0);
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
}
