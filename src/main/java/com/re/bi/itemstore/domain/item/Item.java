package com.re.bi.itemstore.domain.item;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TAB_ITEM")
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
  @SequenceGenerator(name = "item_seq_gen", sequenceName = "SEQ_ITEM_ID", initialValue = 1000, allocationSize = 1)
  @Column(name = "ITEM_ID", nullable = false)
  private Long id;

  @Version
  @Column(name = "ITEM_VERSION", nullable = false)
  private Long version;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_VALUE", nullable = false))
  private ItemValue value;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_TAGS"))
  private ItemTags tags;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_CREATION_DATE_TIME", nullable = false))
  private ItemDateTime creationDateTime;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "ITEM_UPDATE_DATE_TIME", nullable = false))
  private ItemDateTime updateDateTime;

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

  public Long getId() {
    return id;
  }

  public ItemValue getValue() {
    return value;
  }

  public ItemTags getTags() {
    return tags;
  }

  public ItemDateTime getCreationDateTime() {
    return creationDateTime;
  }

  public ItemDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  public void updateItemValue(ItemValue value) {
    this.value = value;
    this.updateDateTime = new ItemDateTime(LocalDateTime.now());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    return Objects.equals(id, item.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
