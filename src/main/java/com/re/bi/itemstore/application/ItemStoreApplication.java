package com.re.bi.itemstore.application;

import com.re.bi.itemstore.ItemStoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ItemStoreConfiguration.class})
public class ItemStoreApplication {
  public static void main(String[] args) {
    SpringApplication.run(ItemStoreApplication.class, args);
  }
}
