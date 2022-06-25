package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.tag.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TAB_ITEM")
public class Item {
  @Id
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_UUID", nullable = false, unique = true))
  private ItemUuid uuid;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_VALUE", nullable = false))
  private ItemValue value;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_CREATION_DATE", nullable = false))
  private ItemDateTime creationTime;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_UPDATE_DATE"))
  private ItemDateTime updateTime;

  @ManyToMany
  @JoinTable(
      name = "TAB_TAGGED_ITEM",
      joinColumns = @JoinColumn(name = "ITEM_UUID"),
      inverseJoinColumns = @JoinColumn(name = "ITAG_ID")
  )
  private Set<Tag> tags;

  protected Item() {
  }

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

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public void addTag(Tag tag) {
    this.tags.add(tag);
  }

  public void updateItemValue(ItemValue value) {
    this.value = value;
    this.updateTime = new ItemDateTime(LocalDateTime.now());
  }
}
