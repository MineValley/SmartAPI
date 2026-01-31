package minevalley.smart.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PurchaseOffer {

    /**
     * The name of the offer.
     * <p>
     * <b>Description:</b> This will be displayed as well in sentences, as in the gui title, therefor keep it short.
     */
    private final String name;

    /**
     * The description of the offer.
     * <p>
     * <b>Description:</b> This will be displayed in the offer gui. Keep it short and informative.
     */
    private final String description;

    /**
     * The item to display for the offer.
     * <p>
     * <b>Note:</b> The name and lore of the item will be overridden - no need to set them.
     */
    private final ItemStack item;

    /**
     * The price of the offer in cents.
     * <p>
     * <b>Note:</b> This needs to be positive or zero.
     */
    private final int priceInCents;

    /**
     * The action that is performed when the offer is accepted, or canceled.
     */
    private final Consumer<Session> onAccept, onCancel;

    /**
     * The IBAN to which the payment should be sent. The default leads to the Smart Coorp.
     */
    private String iban = "DE01-280501";
}