package vw.vorbereitung.warehouse.basketItem.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom object not found exception
 *
 * @author: Daniel Nitschke, Marian Kowall
 */
public class ObjectNotFoundException extends RuntimeException {

  private final HttpStatus status;

  public ObjectNotFoundException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return this.status;
  }
}
