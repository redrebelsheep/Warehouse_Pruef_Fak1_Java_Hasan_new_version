package vw.vorbereitung.warehouse.basketItem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Valid
public class BasketItem {

  private UUID itemNumber;

  /** The product name. */
  @NotNull(message = "product name can not be null")
  private String productName;

  /** The purchasing price. */
  @NotNull(message = " purchasing price. can not be null")
  private double purchasingPrice;

  /** The selling price. */
  @NotNull(message = "product name can not be null")
  private double sellingPrice;

  /** The manufacturer. */
  @NotNull(message = "product name can not be null")
  private String manufacturer;

  /** The amount. */
  @NotNull(message = "product name can not be null")
  private int amount;

  public BasketItem(
      String productName,
      double purchasingPrice,
      double sellingPrice,
      String manufacturer,
      int amount) {
    this.productName = productName;
    this.purchasingPrice = purchasingPrice;
    this.sellingPrice = sellingPrice;
    this.manufacturer = manufacturer;
    this.amount = amount;
  }
}
