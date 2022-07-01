package com.re.bi.itemstore.application.item.search;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemSpecificationBuilder {
  private final List<ItemSearchCriteria> criteriaList;

  public ItemSpecificationBuilder() {
    criteriaList = new ArrayList<>();
  }

  public ItemSpecificationBuilder with(ItemSearchCriteria criteria) {
    criteriaList.add(criteria);
    return this;
  }

  public ItemSpecification build() {
    List<ItemSpecification> specifications = criteriaList.stream()
        .map(c -> c instanceof ItemValueSearchCriteria ? new ItemValueSpecification(c) : new ItemTagsSpecification(c))
        .collect(Collectors.toList());

    Optional<ItemSpecification> optional = specifications.stream().findFirst();
    if (optional.isEmpty()) {
      return null;
    }

    ItemSpecification result = optional.get();
    for (ItemSpecification specification : specifications) {
      if (!specification.equals(result)) {
        result = (ItemSpecification) Specification.where(result).and(specification);
      }
    }

    return result;
  }
}
