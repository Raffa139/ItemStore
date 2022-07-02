package com.re.bi.itemstore.application.item.search;

import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemValue;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class ItemValueSpecification implements ItemSpecification {
  private final ItemSearchCriteria<ItemValue> criteria;

  public ItemValueSpecification(ItemSearchCriteria<ItemValue> criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    String operation = criteria.getOperation().toLowerCase();
    switch (operation) {
      case ">":
        return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().getValue());
      case "<":
        return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().getValue());
      case ":":
        return builder.equal(root.get(criteria.getKey()), criteria.getValue().getValue());
      default:
        throw new UnsupportedOperationException(operation);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemValueSpecification that = (ItemValueSpecification) o;

    return Objects.equals(criteria, that.criteria);
  }

  @Override
  public int hashCode() {
    return criteria != null ? criteria.hashCode() : 0;
  }
}
