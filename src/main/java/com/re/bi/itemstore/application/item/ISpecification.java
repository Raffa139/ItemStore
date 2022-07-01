package com.re.bi.itemstore.application.item;

import com.re.bi.itemstore.application.search.ISearchCriteria;
import com.re.bi.itemstore.application.search.SearchCriteria;
import com.re.bi.itemstore.domain.AbstractValueObject;
import com.re.bi.itemstore.domain.item.Item;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class ISpecification<V extends AbstractValueObject<?>> implements Specification<Item> {
  protected ISearchCriteria<V> criteria;

  public ISpecification(ISearchCriteria<V> criteria) {
    this.criteria = criteria;
  }

  public static ISpecification fromSearch(String search) {
    Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?),");
    Matcher matcher = pattern.matcher(search + ",");

    while (matcher.find()) {
      String searchField = matcher.group(1);
      if (searchField.equals("value")) {

      } else if (searchField.equals("tags")) {

      }
      builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
    }

    specification = builder.build();
  }

  public abstract static class ISpecificationBuilder<V extends AbstractValueObject<?>> {
    private final List<ISearchCriteria<V>> criteriaList;

    public ISpecificationBuilder() {
      criteriaList = new ArrayList<>();
    }

    public ISpecificationBuilder<V> with(String key, String operation, V object) {
      criteriaList.add(new ISearchCriteria<>(key, operation, object));
      return this;
    }

    public Specification<Item> build() {
      List<Specification<Item>> specifications = criteriaList.stream()
          .map(this::instantiate)
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

    protected abstract ISpecification<V> instantiate(ISearchCriteria<V> criteria);
  }
}
