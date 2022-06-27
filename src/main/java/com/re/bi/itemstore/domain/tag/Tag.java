package com.re.bi.itemstore.domain.tag;

import com.re.bi.itemstore.domain.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "TAB_ITEM_TAG")
public class Tag extends AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_tag_seq_gen")
  @SequenceGenerator(name = "item_tag_seq_gen", initialValue = 1000, allocationSize = 1)
  @Column(name = "ITAG_ID", nullable = false)
  private Long id;

  @AttributeOverride(name = "value", column = @Column(name = "ITAG_VALUE", nullable = false, unique = true))
  private TagValue value;

  protected Tag() {
  }

  public Tag(TagValue value) {
    this.value = value;
  }

  @Override
  public Long getId() {
    return id;
  }

  public TagValue getValue() {
    return value;
  }
}
