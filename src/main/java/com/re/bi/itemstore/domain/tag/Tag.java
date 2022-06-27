package com.re.bi.itemstore.domain.tag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TAB_ITEM_TAG")
public class Tag {
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

  public Long getId() {
    return id;
  }

  public TagValue getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tag tag = (Tag) o;

    return Objects.equals(id, tag.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
