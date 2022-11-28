package vw.vorbereitung.warehouse.basketItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

public interface BasketItemRepository extends MongoRepository <BasketItemDocument, Long> {



}
