package vw.vorbereitung.warehouse.basketItem.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Valid
public class BasketItem {

  @NotNull(message = "id can not be null")
  private Long id;

  @NotNull(message = "uuid can not be null")
  private UUID itemNumber;

  /** The product name. */
  @NotNull(message = "product name can not be null")
  private String productName;

  /** The purchasing price. */
  @NotNull(message = "purchasing price. can not be null")
  private double purchasingPrice;

  /** The selling price. */
  @NotNull(message = "selling price can not be null")
  private double sellingPrice;

  /** The manufacturer. */
  @NotNull(message = "manufacturer name can not be null")
  private String manufacturer;

  /** The amount. */
  @NotNull(message = "amount can not be null")
  private int amount;
}
