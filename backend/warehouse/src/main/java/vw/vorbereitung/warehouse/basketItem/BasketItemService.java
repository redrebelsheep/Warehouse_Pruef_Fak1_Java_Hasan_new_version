package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

@AllArgsConstructor
@Service
public class BasketItemService {

    private BasketItemRepository repository;

    public void save(BasketItemDocument document){
        repository.save(document);
    }



}
