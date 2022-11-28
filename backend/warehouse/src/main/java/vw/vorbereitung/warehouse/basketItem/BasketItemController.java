package vw.vorbereitung.warehouse.basketItem;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/item")
public class BasketItemController {

    private final BasketItemService service;

    @GetMapping("/all")
    public  ResponseEntity<List<BasketItemDocument>>getAll() {
        return new ResponseEntity<List<BasketItemDocument>>(service.getAll(), HttpStatus.OK);
    }



}
