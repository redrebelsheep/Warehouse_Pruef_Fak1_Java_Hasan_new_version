package vw.vorbereitung.warehouse.basketItem;

import org.springframework.http.HttpStatus;
import vw.vorbereitung.warehouse.exception.ObjectNotFoundException;

import java.util.UUID;

public class BasketItemItemNumberNotFoundException extends ObjectNotFoundException {

  public BasketItemItemNumberNotFoundException(UUID identifier, HttpStatus status) {
    super("Get : No BasketItem with Item number: " + identifier.toString() + " found", status);
  }
}
