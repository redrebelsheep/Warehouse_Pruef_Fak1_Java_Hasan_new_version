package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vw.vorbereitung.warehouse.basketItem.model.BasketItem;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

import javax.validation.Valid;
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
    this.logger.info("home method assesd");
    return "HOME";
  }

  @GetMapping("/item")
  public ResponseEntity<List<BasketItemDocument>> getAll() {
    this.logger.info("all items was called");
    return new ResponseEntity<List<BasketItemDocument>>(this.service.getAll(), HttpStatus.OK);
  }

  @GetMapping("/item/{itemNumber}")
  public ResponseEntity<BasketItem> get(@PathVariable UUID itemNumber) {
    this.logger.info("one item was called");
    return ResponseEntity.ok(this.service.getBasketItem(itemNumber));
  }

  @DeleteMapping("/item/{itemNumber}")
  public ResponseEntity<String> delete(@PathVariable UUID itemNumber) {
    if (this.service.deletedItem(itemNumber)) {
      this.logger.info("delete one item");
      return new ResponseEntity<>("result successful", HttpStatus.OK);
    }
    this.logger.info("item not found item");
    return new ResponseEntity<>("not Found", HttpStatus.NOT_FOUND);
  }

  @PostMapping("/item")
  public ResponseEntity<BasketItem> save(@RequestBody @Valid BasketItem item) {
    this.logger.info("item was saved");
    BasketItem saveItem = this.service.saveItem(item);
    return getBasketItemDocumentResponseEntity(saveItem);
  }

  @PutMapping("/item")
  public ResponseEntity<BasketItem> update(@RequestBody @Valid BasketItem item) {
    this.logger.info("item was updated");
    BasketItem saveItem = this.service.updateItem(item);
    return getBasketItemDocumentResponseEntity(saveItem);
  }

  private ResponseEntity<BasketItem> getBasketItemDocumentResponseEntity(BasketItem item) {
    if (item != null) {
      return ResponseEntity.created(URI.create("/api/item" + item.getItemNumber())).body(item);
    }
    return ResponseEntity.badRequest().build();
  }
}
