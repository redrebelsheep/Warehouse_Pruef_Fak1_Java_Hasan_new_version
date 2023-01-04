package vw.vorbereitung.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vw.vorbereitung.warehouse.basketItem.BasketItemService;
import vw.vorbereitung.warehouse.basketItem.model.BasketItem;

@SpringBootApplication
@EnableSwagger2
public class WarehouseApplication {

  @Autowired BasketItemService service;

  public static void main(String[] args) {
    SpringApplication.run(WarehouseApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
    System.out.println("hello world, I have just started up");
    BasketItem test =
        BasketItem.builder()
            .productName("Hello")
            .purchasingPrice(2)
            .sellingPrice(1)
            .manufacturer("Manfred")
            .build();
    this.service.saveItem(test);
    BasketItem test2 =
        BasketItem.builder()
            .productName("Hello2")
            .purchasingPrice(2)
            .sellingPrice(1)
            .manufacturer("Manfred2")
            .build();
    this.service.saveItem(test2);
  }
}
