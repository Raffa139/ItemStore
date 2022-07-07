package com.re.bi.itemstore.application;

import com.re.bi.itemstore.domain.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ItemStoreScheduler {
  @Autowired
  private ItemService service;

  @Value("${sys.items.expiration.time-ms}")
  private Long expirationTime;

  @Scheduled(
      fixedDelayString = "${sys.items.expiration.job.delay-ms}",
      initialDelayString = "${sys.items.expiration.job.initial-delay-ms}"
  )
  public void deleteExpiredItems() {
    service.expireItems(expirationTime);
  }
}
