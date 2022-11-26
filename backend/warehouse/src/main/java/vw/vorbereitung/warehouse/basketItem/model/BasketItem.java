package vw.vorbereitung.warehouse.basketItem;
import javafx.beans.property.IntegerProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BasketItem {

    /** The basket item database ID. */
    //Attributes
    private int basketItemDatabaseID;

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
    private IntegerProperty amount;


}
