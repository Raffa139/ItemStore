package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.application.search.ItemValueSearchCriteria;
import com.re.bi.itemstore.domain.item.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ItemValueSpecification implements Specification<Item> {
  private ItemValueSearchCriteria criteria;

  public ItemValueSpecification(ItemValueSearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    String operation = criteria.getOperation().toLowerCase();
    switch (operation) {
      case ">":
        return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().getValue());
      case "<":
        return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().getValue());
      case ":":
          return builder.equal(root.get(criteria.getKey()), criteria.getValue().getValue());
      default:
        throw new UnsupportedOperationException(operation);
    }
  }
}
