package com.re.bi.itemstore.domain;

import java.util.Objects;

public abstract class AbstractValueObject<V> {
  private final V value;

  public AbstractValueObject(V value) {
    if (valid(value)) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Invalid value!");
    }
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
