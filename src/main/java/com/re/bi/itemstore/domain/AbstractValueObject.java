package com.re.bi.itemstore.domain;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractValueObject<V> implements Comparable<V> {
  private V value;

  protected AbstractValueObject() {
  }

  public AbstractValueObject(V value) {
    if (valid(value)) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Invalid value!");
    }
  }

  public V getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AbstractValueObject<?> that = (AbstractValueObject<?>) o;

    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "AbstractValueObject{" +
        "value=" + value +
        '}';
  }

  protected boolean valid(V value) {
    return value != null;
  }
}
