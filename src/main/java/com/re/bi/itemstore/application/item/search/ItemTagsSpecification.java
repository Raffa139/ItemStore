package com.re.bi.itemstore.application.item.search;

import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemTags;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class ItemTagsSpecification implements ItemSpecification {
  private final ItemSearchCriteria<ItemTags> criteria;

  public ItemTagsSpecification(ItemSearchCriteria<ItemTags> criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    // TODO: 01.07.2022: Ignore case
    // TODO: 01.07.2022: Metamodels
    String operation = criteria.getOperation().toLowerCase();
    if (":".equals(operation)) {
      List<String> tags = criteria.getValue().toList();
      return builder.or(tags.stream()
          .map(tag -> builder.like(root.get(criteria.getKey()).get("value"), "%" + tag + "%"))
          .toArray(Predicate[]::new));
    }
    throw new UnsupportedOperationException(operation);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemTagsSpecification that = (ItemTagsSpecification) o;

    return Objects.equals(criteria, that.criteria);
  }

  @Override
  public int hashCode() {
    return criteria != null ? criteria.hashCode() : 0;
  }
}
