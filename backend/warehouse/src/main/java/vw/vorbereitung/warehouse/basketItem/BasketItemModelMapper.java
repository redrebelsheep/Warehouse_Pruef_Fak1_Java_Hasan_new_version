package vw.vorbereitung.warehouse.basketItem;

import org.modelmapper.ModelMapper;
import vw.vorbereitung.warehouse.basketItem.model.BasketItem;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;

public class BasketItemModelMapper {

  private final ModelMapper mapper;

  public BasketItemModelMapper(ModelMapper mapper) {
    this.mapper = mapper;
  }

  public BasketItem convertDocumentToPojo(BasketItemDocument document) {
    return this.mapper.map(document, BasketItem.class);
  }

  public BasketItemDocument convertPojoToDocument(BasketItem pojo) {
    return this.mapper.map(pojo, BasketItemDocument.class);
  }
}
