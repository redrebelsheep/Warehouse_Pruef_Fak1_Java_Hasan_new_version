package vw.vorbereitung.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vw.vorbereitung.warehouse.basketItem.BasketItemService;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

import java.util.UUID;

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
    BasketItemDocument test =
        BasketItemDocument.builder()
            .itemNumber((UUID.randomUUID()))
            .productName("Hello")
            .purchasingPrice(2)
            .sellingPrice(1)
            .manufacturer("Manfred")
            .build();

    this.service.save(test);
  }
}
