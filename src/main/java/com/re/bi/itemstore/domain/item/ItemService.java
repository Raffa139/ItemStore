package com.re.bi.itemstore.domain.item;

import com.re.bi.itemstore.application.item.search.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
  @Autowired
  private ItemRepository repository;

  @Autowired
  private CacheManager cacheManager;

  @Cacheable("items")
  public Page<Item> findPage(ItemSpecification specification, Pageable pageable) {
    return repository.findAll(specification, pageable);
  }

  public Item findById(Long id) {
    Optional<Item> item = repository.findById(id);

    if (item.isEmpty()) {
      throw new ItemNotFoundException();
    }

    return item.get();
  }

  public Item save(Item item) {
    return repository.save(item);
  }

  @Transactional
  public void update(Item item, ItemValue newValue) {
    if (!item.getValue().equals(newValue)) {
      item.updateItemValue(newValue);
      cacheManager.getCache("items").clear();
    }
  }

  public void expireItems(long expirationTime) {
    ItemDateTime expiredDateTime = new ItemDateTime(LocalDateTime.now().minus(expirationTime, ChronoUnit.MILLIS));
    List<Item> expiredItems = repository.findAllExpired(expiredDateTime);

    if (expiredItems.size() > 0) {
      repository.deleteAll(expiredItems);
      cacheManager.getCache("items").clear();
    }
  }
}
