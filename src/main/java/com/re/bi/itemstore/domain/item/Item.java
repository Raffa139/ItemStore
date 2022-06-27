package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractEntity;
import com.re.bi.itemstore.domain.tag.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAB_ITEM")
public class Item extends AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
  @SequenceGenerator(name = "item_seq_gen", initialValue = 1000, allocationSize = 1)
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

  @ManyToMany
  @JoinTable(
      name = "TAB_TAGGED_ITEM",
      joinColumns = @JoinColumn(name = "ITEM_ID"),
      inverseJoinColumns = @JoinColumn(name = "ITAG_ID")
  )
  private Set<Tag> tags;

  protected Item() {
  }

  public Item(ItemValue value) {
    this.value = value;
    this.creationDateTime = new ItemDateTime(LocalDateTime.now());
    this.tags = new HashSet<>();
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
    this.updateDateTime = new ItemDateTime(LocalDateTime.now());
  }
}
