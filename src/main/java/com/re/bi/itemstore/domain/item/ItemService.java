package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.application.item.search.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ItemService {
  @Autowired
  private ItemRepository repository;

  public Page<Item> findPage(ItemSpecification specification, Pageable pageable) {
    return repository.findAll(specification, pageable);
  }

  public Item findById(Long id) {
    Optional<Item> item = repository.findById(id);

    if (item.isEmpty()) {
      throw new EntityNotFoundException();
    }

    return item.get();
  }

  public Item save(Item item) {
    return repository.save(item);
  }

  public Item update(Item item, ItemValue newValue) {
    if (!item.getValue().equals(newValue)) {
      item.updateItemValue(newValue);
      return repository.save(item);
    }

    return null;
  }
}
