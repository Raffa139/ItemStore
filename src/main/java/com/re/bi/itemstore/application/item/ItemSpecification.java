package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.application.search.SearchCriteria;
import com.re.bi.itemstore.domain.item.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemSpecification implements Specification<Item> {
  private SearchCriteria criteria;

  public ItemSpecification(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    String operation = criteria.getOperation().toLowerCase();
    switch (operation) {
      case ">":
        return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
      case "<":
        return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
      case ":":
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
          return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        } else {
          return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
      default:
        throw new UnsupportedOperationException(operation);
    }
  }

  public static ItemSpecificationBuilder newItemSpecification() {
    return new ItemSpecificationBuilder();
  }

  public static class ItemSpecificationBuilder {
    private List<SearchCriteria> criteriaList;

    public ItemSpecificationBuilder() {
      criteriaList = new ArrayList<>();
    }

    public ItemSpecificationBuilder with(String key, String operation, String object) {
      return with(new SearchCriteria(key, operation, object));
    }

    public ItemSpecificationBuilder with(SearchCriteria criteria) {
      criteriaList.add(criteria);
      return this;
    }

    public Specification<Item> build() {
      List<Specification<Item>> specifications = criteriaList.stream()
          .map(ItemSpecification::new)
          .collect(Collectors.toList());

      Optional<Specification<Item>> optional = specifications.stream().findFirst();

      if (optional.isEmpty()) {
        return null;
      }

      Specification<Item> result = optional.get();
      for (Specification<Item> specification : specifications) {
        result = Specification.where(result).and(specification);
      }

      return result;
    }
  }
}
