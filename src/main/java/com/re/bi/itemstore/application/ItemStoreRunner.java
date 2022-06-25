package com.re.bi.itemstore.application;

import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemRepository;
import com.re.bi.itemstore.domain.item.ItemValue;
import com.re.bi.itemstore.domain.tag.Tag;
import com.re.bi.itemstore.domain.tag.TagRepository;
import com.re.bi.itemstore.domain.tag.TagValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ItemStoreRunner implements CommandLineRunner {
  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private TagRepository tagRepository;

  @Override
  public void run(String... args) {
    Set<Tag> tags = new HashSet<>();
    for (int i = 0; i < 10; i++) {
      tags.add(new Tag(new TagValue("Tag" + i)));
    }

    tagRepository.saveAll(tags);

    Item item = new Item(new ItemValue(10));
    item.setTags(tags);
    itemRepository.save(item);
  }
}
