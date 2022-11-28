package vw.vorbereitung.warehouse.basketItem;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BasketItemController {

    private final BasketItemService service;

    @GetMapping("/item/all")
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
            return new ResponseEntity<>("result successful result",
                                        HttpStatus.OK);
        }
        return new ResponseEntity<>("not Found", HttpStatus.NOT_FOUND);
    }
}
