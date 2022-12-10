package vw.vorbereitung.warehouse.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * class to write in the exceptions an own description
 *
 * @author: Daniel Nitschke, Marian Kowall
 */
@Getter
@ToString
public class CustomApiDescriptionProblem {

  private final String message;
  private final HttpStatus status;
  private final ZonedDateTime timeStamp;
  private Map<String, String> errorMap = new HashMap<>();

  public CustomApiDescriptionProblem(String message, HttpStatus status, ZonedDateTime timeStamp) {
    this.message = message;
    this.status = status;
    this.timeStamp = timeStamp;
  }

  public CustomApiDescriptionProblem(
      String message, HttpStatus status, ZonedDateTime timeStamp, Map<String, String> errorMap) {
    this.message = message;
    this.status = status;
    this.timeStamp = timeStamp;
    this.errorMap = errorMap;
  }
}
