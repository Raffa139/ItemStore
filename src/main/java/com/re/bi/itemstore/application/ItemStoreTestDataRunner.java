package com.re.bi.itemstore.application;

import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemRepository;
import com.re.bi.itemstore.domain.item.ItemTags;
import com.re.bi.itemstore.domain.item.ItemValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ItemStoreTestDataRunner implements CommandLineRunner {
  @Autowired
  private ItemRepository itemRepository;

  @Override
  public void run(String... args) {
    Set<String> tags = Set.of("ABC", "XYZ", "123");
    List<Item> items = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      items.add(new Item(new ItemValue(i*10+100), new ItemTags(tags)));
    }
    itemRepository.saveAll(items);
  }
}
