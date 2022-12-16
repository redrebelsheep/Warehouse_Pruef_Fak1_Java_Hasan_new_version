package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vw.vorbereitung.warehouse.basketItem.model.BasketItem;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;
import vw.vorbereitung.warehouse.sequencegenerator.SequenceGeneratorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BasketItemService {

  private BasketItemRepository repository;

  private SequenceGeneratorService sequenceGeneratorService;

  private BasketItemModelMapper mapper;

  public List<BasketItem> getAll() {
    return this.repository.findAll().stream()
        .map(element -> this.mapper.convertDocumentToPojo(element))
        .collect(Collectors.toList());
  }

  public BasketItem getBasketItem(UUID itemNumber) {
    Optional<BasketItemDocument> document = this.repository.findByItemNumber(itemNumber);
    if (!document.isPresent()) {
      throw new BasketItemItemNumberNotFoundException(itemNumber, HttpStatus.NO_CONTENT);
    }
    return this.mapper.convertDocumentToPojo(document.get());
  }

  public boolean deleteItem(UUID itemNumber) {
    if (this.repository.findByItemNumber(itemNumber).isPresent()) {
      this.repository.deleteByItemNumber(itemNumber);
      return true;
    }
    return false;
  }

  public BasketItem saveItem(BasketItem item) {
    BasketItemDocument document = this.mapper.convertPojoToDocument(item);
    document.setId(
        this.sequenceGeneratorService.generateSequence(BasketItemDocument.SEQUENCE_NAME));
    document.setItemNumber(UUID.randomUUID());
    document = this.repository.save(document);
    return this.mapper.convertDocumentToPojo(document);
  }

  public BasketItem updateItem(BasketItem item) {
    BasketItemDocument document = this.mapper.convertPojoToDocument(item);
    UUID itemNumber = item.getItemNumber();
    Optional<BasketItemDocument> optionalDocument = this.repository.findByItemNumber(itemNumber);
    if (!optionalDocument.isPresent()) {
      throw new BasketItemItemNumberNotFoundException(itemNumber, HttpStatus.NO_CONTENT);
    }
    document.setId(optionalDocument.get().getId());
    document = this.repository.save(document);
    return this.mapper.convertDocumentToPojo(document);
  }
}
