package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
  @Autowired
  private ItemRepository itemRepository;

  @GetMapping
  public ResponseEntity<CollectionModel<ItemModel>> getItems(@PageableDefault(Integer.MAX_VALUE) Pageable pageable) {
    Page<Item> items = itemRepository.findAll(pageable);
    return ResponseEntity.ok(CollectionModel.of(items.stream()
        .map(ItemModel::new)
        .collect(Collectors.toList())));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ItemModel> getItem(@PathVariable Long id) {
    Optional<Item> optional = itemRepository.findById(id);

    if (optional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(new ItemModel(optional.get()));
  }
}
