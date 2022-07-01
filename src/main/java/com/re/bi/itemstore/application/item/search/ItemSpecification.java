package com.re.bi.itemstore.application.item.search;

import com.re.bi.itemstore.domain.item.Item;
import org.springframework.data.jpa.domain.Specification;

public interface ItemSpecification extends Specification<Item> {
}
