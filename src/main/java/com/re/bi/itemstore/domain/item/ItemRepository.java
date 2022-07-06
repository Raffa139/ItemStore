package com.re.bi.itemstore.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
  @Query("SELECT i FROM Item i WHERE i.updateDateTime <= :expiredDateTime")
  List<Item> findAllExpired(ItemDateTime expiredDateTime);
}
