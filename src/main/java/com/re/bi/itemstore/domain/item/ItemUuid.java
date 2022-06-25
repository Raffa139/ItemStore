package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.domain.AbstractValueObject;

import java.util.UUID;

public class ItemUuid extends AbstractValueObject<UUID> {
  public ItemUuid(UUID value) {
    super(value);
  }
}
