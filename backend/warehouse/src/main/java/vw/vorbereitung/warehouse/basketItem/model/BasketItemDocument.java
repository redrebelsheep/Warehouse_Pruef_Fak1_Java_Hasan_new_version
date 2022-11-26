package vw.vorbereitung.warehouse.basketItem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class BasketItemDocument {

    @Transient
    public static final String SEQUENCE_NAME = "basketItem_sequence";

    @Id
    private Long id;

    /** The item number. */
    private int itemNumber;

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
