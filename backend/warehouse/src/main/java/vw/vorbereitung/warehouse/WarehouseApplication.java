package vw.vorbereitung.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import vw.vorbereitung.warehouse.basketItem.BasketItemRepository;
import vw.vorbereitung.warehouse.basketItem.BasketItemService;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

@SpringBootApplication
public class WarehouseApplication {

	@Autowired
	BasketItemService service;

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("hello world, I have just started up");
		BasketItemDocument test = BasketItemDocument.builder()
													.productName("Hello")
													.purchasingPrice(2)
													.sellingPrice(1).manufacturer("Manfred").build();

		service.save(test);
	}
}
