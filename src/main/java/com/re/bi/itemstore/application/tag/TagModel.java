package com.re.bi.itemstore.application.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.re.bi.itemstore.domain.tag.Tag;
import com.re.bi.itemstore.domain.tag.TagValue;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Objects;

@Relation(collectionRelation = "models")
public class TagModel extends RepresentationModel<TagModel> {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("value")
  @JsonSerialize(converter = TagValueConverter.class)
  private TagValue value;

  public TagModel(Tag tag) {
    this.id = tag.getId();
    this.value = tag.getValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    TagModel tagModel = (TagModel) o;

    return Objects.equals(id, tagModel.id);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (id != null ? id.hashCode() : 0);
    return result;
  }
}
