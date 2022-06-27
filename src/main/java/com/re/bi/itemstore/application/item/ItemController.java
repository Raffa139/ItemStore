package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
  @Autowired
  private ItemRepository itemRepository;

  @GetMapping
  public ResponseEntity<CollectionModel<ItemModel>> getItems() {
    List<Item> items = itemRepository.findAll();
    return ResponseEntity.ok(CollectionModel.of(items.stream()
        .map(ItemModel::new)
        .collect(Collectors.toList())));
  }
}
