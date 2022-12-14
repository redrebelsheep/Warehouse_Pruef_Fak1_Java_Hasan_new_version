package vw.vorbereitung.warehouse.basketItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

import java.util.Optional;
import java.util.UUID;

public interface BasketItemRepository extends MongoRepository<BasketItemDocument, Long> {

  Optional<BasketItemDocument> findByItemNumber(UUID identifier);

  void deleteByItemNumber(UUID itemNumber);
}
