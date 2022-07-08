package com.re.bi.itemstore.application.item.search;

import com.re.bi.itemstore.domain.item.Item;
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

  public Specification<Item> build() {
    List<Specification<Item>> specifications = criteriaList.stream()
        .map(c -> c instanceof ItemValueSearchCriteria ? new ItemValueSpecification(c) : new ItemTagsSpecification(c))
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
