package vw.vorbereitung.warehouse.basketItem;


import lombok.AllArgsConstructor;
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

    @GetMapping("/item")
    public ResponseEntity<List<BasketItemDocument>>getAll() {
        return new ResponseEntity<List<BasketItemDocument>>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/item/{itemNumber}")
    public ResponseEntity<BasketItemDocument>get(@PathVariable UUID itemNumber){
        return ResponseEntity.ok(service.getBasketItem(itemNumber));
    }

    @DeleteMapping("/item/{itemNumber}")
    public ResponseEntity<String> delete(@PathVariable UUID itemNumber){
        if(service.deletedItem(itemNumber)){
            return new ResponseEntity<>("result successful",
                                        HttpStatus.OK);
        }
        return new ResponseEntity<>("not Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/item")
    public ResponseEntity<BasketItemDocument> save(@RequestBody BasketItemDocument item){
        BasketItemDocument saveItem = this.service.saveItem(item);
        if(saveItem != null){
            return ResponseEntity.created(
                            URI.create("/api/item" + saveItem.getItemNumber()))
                    .body(saveItem);
        }
        return ResponseEntity.badRequest().build();
    }




}
