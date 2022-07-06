package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAB_ITEM")
@AttributeOverride(name = "version", column = @Column(name = "ITEM_VERSION", nullable = false))
public class Item extends AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
  @SequenceGenerator(name = "item_seq_gen", sequenceName = "SEQ_ITEM_ID", initialValue = 1000, allocationSize = 1)
  @Column(name = "ITEM_ID", nullable = false)
  private Long id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_VALUE", nullable = false))
  private ItemValue value;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_CREATION_DATE", nullable = false))
  private ItemDateTime creationDateTime;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_UPDATE_DATE"))
  private ItemDateTime updateDateTime;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_TAGS"))
  private ItemTags tags;

  protected Item() {
  }

  public Item(ItemValue value, ItemTags tags) {
    this(value);
    this.tags = tags;
  }

  public Item(ItemValue value) {
    LocalDateTime now = LocalDateTime.now();
    this.value = value;
    this.creationDateTime = new ItemDateTime(now);
    this.updateDateTime = new ItemDateTime(now);
  }

  @Override
  public Long getId() {
    return id;
  }

  public ItemValue getValue() {
    return value;
  }

  public ItemDateTime getCreationDateTime() {
    return creationDateTime;
  }

  public ItemDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  public ItemTags getTags() {
    return tags;
  }

  public void updateItemValue(ItemValue value) {
    this.value = value;
    this.updateDateTime = new ItemDateTime(LocalDateTime.now());
  }
}
