package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vw.vorbereitung.warehouse.basketItem.model.BasketItem;

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

  @GetMapping("/item")
  public ResponseEntity<List<BasketItem>> getAll() {
    this.logger.info("all items was called");
    List<BasketItem> list = this.service.getAll();
    return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
  }

  @GetMapping("/item/{itemNumber}")
  public ResponseEntity<BasketItem> getOne(@PathVariable UUID itemNumber) {
    this.logger.info("one item was called");
    return ResponseEntity.ok(this.service.getBasketItem(itemNumber));
  }

  @DeleteMapping("/item/{itemNumber}")
  public ResponseEntity<String> delete(@PathVariable UUID itemNumber) {
    if (this.service.deleteItem(itemNumber)) {
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
    BasketItem updateItem = this.service.updateItem(item);
    return getBasketItemDocumentResponseEntity(updateItem);
  }

  private ResponseEntity<BasketItem> getBasketItemDocumentResponseEntity(BasketItem item) {
    if (item != null) {
      return ResponseEntity.created(URI.create("/api/item" + item.getItemNumber())).body(item);
    }
    return ResponseEntity.badRequest().build();
  }
}
