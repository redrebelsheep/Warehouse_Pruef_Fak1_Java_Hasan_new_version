package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BasketItemController {

  private final BasketItemService service;

  private final Logger logger = LoggerFactory.getLogger(BasketItemController.class);

  @RequestMapping("/")
  public String home() {
    this.logger.info("Logger works");
    return "HOME";
  }

  @GetMapping("/item")
  public ResponseEntity<List<BasketItemDocument>> getAll() {
    return new ResponseEntity<List<BasketItemDocument>>(this.service.getAll(), HttpStatus.OK);
  }

  @GetMapping("/item/{itemNumber}")
  public ResponseEntity<BasketItemDocument> get(@PathVariable UUID itemNumber) {
    return ResponseEntity.ok(this.service.getBasketItem(itemNumber));
  }

  @DeleteMapping("/item/{itemNumber}")
  public ResponseEntity<String> delete(@PathVariable UUID itemNumber) {
    if (this.service.deletedItem(itemNumber)) {

      return new ResponseEntity<>("result successful", HttpStatus.OK);
    }
    return new ResponseEntity<>("not Found", HttpStatus.NOT_FOUND);
  }

  @PostMapping("/item")
  public ResponseEntity<BasketItemDocument> save(@RequestBody BasketItemDocument item) {
    BasketItemDocument saveItem = this.service.saveItem(item);
    return getBasketItemDocumentResponseEntity(saveItem);
  }

  @PutMapping("/item")
  public ResponseEntity<BasketItemDocument> update(@RequestBody BasketItemDocument item) {
    BasketItemDocument saveItem = this.service.updateItem(item);
    return getBasketItemDocumentResponseEntity(saveItem);
  }

  private ResponseEntity<BasketItemDocument> getBasketItemDocumentResponseEntity(
      BasketItemDocument item) {
    if (item != null) {
      return ResponseEntity.created(URI.create("/api/item" + item.getItemNumber())).body(item);
    }
    return ResponseEntity.badRequest().build();
  }
}
