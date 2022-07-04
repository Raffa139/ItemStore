package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.domain.item.Item;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler extends RepresentationModelAssemblerSupport<Item, ItemModel> {
  public ItemModelAssembler() {
    super(ItemController.class, ItemModel.class);
  }

  @Override
  public ItemModel toModel(Item entity) {
    return new ItemModel(entity)
        .add(linkTo(ItemController.class).withRel("all"))
        .add(linkTo(methodOn(ItemController.class).getItem(entity.getId())).withSelfRel());
  }
}
