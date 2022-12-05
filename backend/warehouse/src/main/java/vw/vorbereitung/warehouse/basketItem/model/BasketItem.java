package vw.vorbereitung.warehouse.basketItem.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BasketItem {

  private UUID itemNumber;

  /** The product name. */
  private String productName;

  /** The purchasing price. */
  private double purchasingPrice;

  /** The selling price. */
  private double sellingPrice;

  /** The manufacturer. */
  private String manufacturer;

  /** The amount. */
  private int amount;
}
