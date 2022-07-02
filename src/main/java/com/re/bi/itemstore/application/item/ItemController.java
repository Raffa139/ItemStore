package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.application.item.search.ItemSpecification;
import com.re.bi.itemstore.application.item.search.ItemSpecificationBuilder;
import com.re.bi.itemstore.application.item.search.ItemTagsSearchCriteria;
import com.re.bi.itemstore.application.item.search.ItemValueSearchCriteria;
import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemRepository;
import com.re.bi.itemstore.domain.item.ItemTags;
import com.re.bi.itemstore.domain.item.ItemValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
  @Autowired
  private ItemRepository itemRepository;

  @GetMapping
  public ResponseEntity<CollectionModel<ItemModel>> getItems(@PageableDefault(Integer.MAX_VALUE) Pageable pageable,
                                                             @RequestParam(value = "search", required = false) String search) {
    ItemSpecification specification = null;

    // TODO: 01.07.2022: Error handling
    if (search != null) {
      ItemSpecificationBuilder builder = new ItemSpecificationBuilder();
      Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?|\\(([\\w;]+?)\\)),");
      Matcher matcher = pattern.matcher(search + ",");
      while (matcher.find()) {
        String searchField = matcher.group(1);
        if (searchField.equals("value")) {
          builder.with(new ItemValueSearchCriteria(matcher.group(1), matcher.group(2), new ItemValue(Integer.parseInt(matcher.group(3)))));
        } else if (searchField.equals("tags")) {
          builder.with(new ItemTagsSearchCriteria(matcher.group(1), matcher.group(2), new ItemTags(matcher.group(4))));
        }
      }
      specification = builder.build();
    }

    Page<Item> items = itemRepository.findAll(specification, pageable);
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
