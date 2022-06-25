package com.re.bi.itemstore.domain.item;

import java.time.LocalDateTime;
import java.util.UUID;

public class Item {
  private ItemUuid uuid;

  private ItemValue value;

  private ItemDateTime creationTime;

  private ItemDateTime updateTime;

  public Item(ItemValue value) {
    this.uuid = new ItemUuid(UUID.randomUUID());
    this.value = value;
    this.creationTime = new ItemDateTime(LocalDateTime.now());
  }

  public ItemUuid getUuid() {
    return uuid;
  }

  public ItemValue getValue() {
    return value;
  }

  public ItemDateTime getCreationTime() {
    return creationTime;
  }

  public ItemDateTime getUpdateTime() {
    return updateTime;
  }

  public void updateItemValue(ItemValue value) {
    this.value = value;
    this.updateTime = new ItemDateTime(LocalDateTime.now());
  }
}
