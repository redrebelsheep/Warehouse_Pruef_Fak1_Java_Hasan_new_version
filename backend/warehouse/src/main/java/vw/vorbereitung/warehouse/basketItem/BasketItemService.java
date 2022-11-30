package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;
import vw.vorbereitung.warehouse.sequencegenerator.SequenceGeneratorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BasketItemService {

  private BasketItemRepository repository;

  private SequenceGeneratorService sequenceGeneratorService;

  public List<BasketItemDocument> getAll() {
    return this.repository.findAll();
  }

  public void save(BasketItemDocument document) {
    document.setId(
        this.sequenceGeneratorService.generateSequence(BasketItemDocument.SEQUENCE_NAME));
    this.repository.save(document);
  }

  public BasketItemDocument getBasketItem(UUID itemNumber) {
    Optional<BasketItemDocument> document = this.repository.findByItemNumber(itemNumber);
    if (!document.isPresent()) {}

    return document.get();
  }

  public boolean deletedItem(UUID itemNumber) {
    if (this.repository.findByItemNumber(itemNumber).isPresent()) {
      this.repository.deleteByItemNumber(itemNumber);
      return true;
    }
    return false;
  }

  public Optional<BasketItemDocument> checkItemIsPresent(UUID itemNumber) {
    return this.repository.findByItemNumber(itemNumber);
  }

  public BasketItemDocument saveItem(BasketItemDocument item) {
    return this.repository.save(item);
  }

  public BasketItemDocument updateItem(BasketItemDocument item) {
    UUID itemNumber = item.getItemNumber();
    Optional<BasketItemDocument> optionalDocument = this.repository.findByItemNumber(itemNumber);
    if (!optionalDocument.isPresent()) {
      return new BasketItemDocument();
    }
    item.setId(optionalDocument.get().getId());
    item = this.repository.save(item);
    return item;
  }
}
