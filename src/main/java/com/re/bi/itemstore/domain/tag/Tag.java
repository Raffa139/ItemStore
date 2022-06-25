package com.re.bi.itemstore.domain.tag;

import javax.persistence.*;

@Entity
@Table(name = "TAB_ITEM_TAG")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_tag_seq_gen")
  @SequenceGenerator(name = "item_tag_seq_gen", initialValue = 1000, allocationSize = 1)
  @Column(name = "ITAG_ID")
  private Long id;

  @AttributeOverride(name = "value", column = @Column(name = "ITAG_VALUE", nullable = false, unique = true))
  private TagValue value;

  protected Tag() {
  }

  public Tag(TagValue value) {
    this.value = value;
  }

  public TagValue getValue() {
    return value;
  }
}
