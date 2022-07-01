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
import java.util.Random;
import java.util.Set;

@Component
public class ItemStoreTestDataRunner implements CommandLineRunner {
  @Autowired
  private ItemRepository itemRepository;

  @Override
  public void run(String... args) {
    Set<String> tagSetOne = Set.of("ABC", "XYZ", "123");
    Set<String> tagSetTwo = Set.of("FFF", "456", "REN");
    Random random = new Random();
    List<Item> items = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      items.add(new Item(new ItemValue(i*10+100), new ItemTags(random.nextFloat() > 0.5f ? tagSetOne : tagSetTwo)));
    }
    itemRepository.saveAll(items);
  }
}
