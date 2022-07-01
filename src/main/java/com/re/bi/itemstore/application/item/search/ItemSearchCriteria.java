package com.re.bi.itemstore.application.item.search;

import com.re.bi.itemstore.domain.AbstractValueObject;

public interface ItemSearchCriteria<V extends AbstractValueObject<?>> {
  String getKey();

  String getOperation();

  V getValue();
}
