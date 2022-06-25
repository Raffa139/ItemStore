package com.re.bi.itemstore.domain.item;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Item {
  private ItemUuid uuid;

  private ItemValue value;

  private ItemDateTime creationTime;

  private ItemDateTime updateTime;

  private Set<ItemTag> tags;

  public Item(ItemValue value) {
    this.uuid = new ItemUuid(UUID.randomUUID());
    this.value = value;
    this.creationTime = new ItemDateTime(LocalDateTime.now());
    this.tags = new HashSet<>();
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

  public Set<ItemTag> getTags() {
    return tags;
  }

  public void updateItemValue(ItemValue value) {
    this.value = value;
    this.updateTime = new ItemDateTime(LocalDateTime.now());
  }
}
