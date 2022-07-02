package com.re.bi.itemstore.domain.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  @Autowired
  private ItemRepository repository;

  public void updateItem(Item item, ItemValue newValue) {
    if (!item.getValue().equals(newValue)) {
      item.updateItemValue(newValue);
      repository.save(item);
    }
  }
}
