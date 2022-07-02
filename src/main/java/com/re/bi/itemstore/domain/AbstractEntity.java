package com.re.bi.itemstore.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {
  @Version
  private Long version;

  protected AbstractEntity() {
  }

  public abstract Long getId();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AbstractEntity e = (AbstractEntity) o;

    return Objects.equals(getId(), e.getId());
  }

  @Override
  public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }
}
