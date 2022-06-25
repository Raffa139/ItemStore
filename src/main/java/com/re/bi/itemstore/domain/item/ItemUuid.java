package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ItemUuid extends AbstractValueObject<UUID> implements Serializable {
  protected ItemUuid() {
  }

  public ItemUuid(UUID value) {
    super(value);
  }
}
