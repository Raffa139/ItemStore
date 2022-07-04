package com.re.bi.itemstore.application.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.re.bi.itemstore.application.item.converter.*;
import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemDateTime;
import com.re.bi.itemstore.domain.item.ItemTags;
import com.re.bi.itemstore.domain.item.ItemValue;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Objects;

@Relation(collectionRelation = "models")
public class ItemModel extends RepresentationModel<ItemModel> {
  @JsonView(ItemViews.IdOnlyResponse.class)
  @JsonProperty("id")
  private Long id;

  @JsonView({ItemViews.CreateRequest.class, ItemViews.UpdateRequest.class})
  @JsonProperty("value")
  @JsonSerialize(converter = ItemValueConverterOut.class)
  @JsonDeserialize(converter = ItemValueConverterIn.class)
  private ItemValue value;

  @JsonProperty("creationDateTime")
  @JsonSerialize(converter = ItemDateTimeConverterOut.class)
  private ItemDateTime creationDateTime;

  @JsonProperty("updateDateTime")
  @JsonSerialize(converter = ItemDateTimeConverterOut.class)
  private ItemDateTime updateDateTime;

  @JsonView(ItemViews.CreateRequest.class)
  @JsonProperty("tags")
  @JsonSerialize(converter = ItemTagsConverterOut.class)
  @JsonDeserialize(converter = ItemTagsConverterIn.class)
  private ItemTags tags;

  protected ItemModel() {
  }

  protected ItemModel(Item item) {
    this.id = item.getId();
    this.value = item.getValue();
    this.creationDateTime = item.getCreationDateTime();
    this.updateDateTime = item.getUpdateDateTime();
    this.tags = item.getTags();
  }

  public ItemValue getValue() {
    return value;
  }

  public ItemTags getTags() {
    return tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    ItemModel itemModel = (ItemModel) o;

    return Objects.equals(id, itemModel.id);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (id != null ? id.hashCode() : 0);
    return result;
  }
}
