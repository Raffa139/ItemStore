package com.re.bi.itemstore.application.item;

import com.fasterxml.jackson.annotation.JsonView;
import com.re.bi.itemstore.application.item.search.ItemSpecification;
import com.re.bi.itemstore.application.item.search.ItemSpecificationBuilder;
import com.re.bi.itemstore.application.item.search.ItemTagsSearchCriteria;
import com.re.bi.itemstore.application.item.search.ItemValueSearchCriteria;
import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemService;
import com.re.bi.itemstore.domain.item.ItemTags;
import com.re.bi.itemstore.domain.item.ItemValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
  @Autowired
  private ItemService service;

  @GetMapping
  public ResponseEntity<CollectionModel<ItemModel>> getItems(@PageableDefault(Integer.MAX_VALUE) Pageable pageable,
                                                             @RequestParam(value = "search", required = false) String search) {
    ItemSpecification specification = null;

    // TODO: 02.07.2022: Links
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

    Page<Item> items = service.findPage(specification, pageable);
    return ResponseEntity.ok(CollectionModel.of(items.stream()
        .map(ItemModel::new)
        .collect(Collectors.toList())));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ItemModel> getItem(@PathVariable Long id) {
    Item item = service.findById(id);
    return ResponseEntity.ok(new ItemModel(item));
  }

  @JsonView(ItemViews.IdOnlyResponse.class)
  @PostMapping(path = "/create")
  public ResponseEntity<ItemModel> createItem(@RequestBody @JsonView(ItemViews.CreateRequest.class) ItemModel model) {
    Item item = new Item(model.getValue(), model.getTags());
    item = service.save(item);

    return ResponseEntity
        .created(linkTo(methodOn(ItemController.class).getItem(item.getId())).toUri())
        .body(new ItemModel(item));
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateItem(@PathVariable Long id, @RequestBody @JsonView(ItemViews.UpdateRequest.class) ItemModel model) {
    Item item = service.findById(id);
    service.update(item, model.getValue());
  }

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<String> handleOptimisticLockExceptions(ObjectOptimisticLockingFailureException e) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body("Concurrent access error.");
  }
}
