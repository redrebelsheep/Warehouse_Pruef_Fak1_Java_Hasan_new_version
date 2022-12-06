package vw.vorbereitung.warehouse.basketItem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Global Exception handler
 *
 * @author: Daniel Nitschke, Marian Kowall
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({ObjectNotFoundException.class})
  public ResponseEntity<Object> handleHelloException(ObjectNotFoundException exception) {
    HttpStatus status = exception.getStatus();
    CustomApiDescriptionProblem descriptionProblem =
        new CustomApiDescriptionProblem(
            exception.getMessage(), status, ZonedDateTime.now(ZoneId.of("Europe/Berlin")));
    return new ResponseEntity<>(descriptionProblem, status);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException exception) {
    Map<String, String> errorMap = new HashMap<>();
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
    CustomApiDescriptionProblem apiException =
        new CustomApiDescriptionProblem(
            "ValidationError",
            BAD_REQUEST,
            ZonedDateTime.now(ZoneId.of("Europe/Berlin")),
            errorMap);
    return new ResponseEntity<>(apiException, BAD_REQUEST);
  }
}
