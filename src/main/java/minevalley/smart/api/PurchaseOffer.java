package minevalley.smart.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PurchaseOffer {
    private final String name, description, skull;
    private final int priceInCents;
    private final Consumer<AppSession> onAccept, onCancel;
    private String iban = "DE01-280501";
}