package com.re.bi.itemstore.application.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.re.bi.itemstore.application.tag.TagModel;
import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemDateTime;
import com.re.bi.itemstore.domain.item.ItemValue;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Relation(collectionRelation = "models")
public class ItemModel extends RepresentationModel<ItemModel> {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("value")
  @JsonSerialize(converter = ItemValueConverter.class)
  private ItemValue value;

  @JsonProperty("creation_date_time")
  @JsonSerialize(converter = ItemDateTimeConverter.class)
  private ItemDateTime creationDateTime;

  @JsonProperty("update_date_time")
  @JsonSerialize(converter = ItemDateTimeConverter.class)
  private ItemDateTime updateDateTime;

  @JsonProperty("tags")
  private Set<TagModel> tags;

  public ItemModel(Item item) {
    this.id = item.getId();
    this.value = item.getValue();
    this.creationDateTime = item.getCreationDateTime();
    this.updateDateTime = item.getUpdateDateTime();
    this.tags = item.getTags().stream()
        .map(TagModel::new)
        .collect(Collectors.toSet());
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