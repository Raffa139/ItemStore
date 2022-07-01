package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.domain.item.Item;
import com.re.bi.itemstore.domain.item.ItemValue;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public static ItemValueSpecificationBuilder newItemValueSpecification() {
    return new ItemValueSpecificationBuilder();
  }

  public static class ItemValueSpecificationBuilder {
    private final List<ItemValueSearchCriteria> criteriaList;

    public ItemValueSpecificationBuilder() {
      criteriaList = new ArrayList<>();
    }

    public ItemValueSpecificationBuilder with(String key, String operation, ItemValue value) {
      criteriaList.add(new ItemValueSearchCriteria(key, operation, value));
      return this;
    }

    public Specification<Item> build() {
      List<Specification<Item>> specifications = criteriaList.stream()
          .map(ItemValueSpecification::new)
          .collect(Collectors.toList());

      Optional<Specification<Item>> optional = specifications.stream().findFirst();

      if (optional.isEmpty()) {
        return null;
      }

      Specification<Item> result = optional.get();
      for (Specification<Item> specification : specifications) {
        if (!specification.equals(result)) {
          result = Specification.where(result).and(specification);
        }
      }

      return result;
    }
  }
}
