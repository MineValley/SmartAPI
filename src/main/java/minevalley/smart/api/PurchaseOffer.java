package minevalley.smart.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class PurchaseOffer {
    private final String name, description, skull;
    private final int priceInCents;
    private final Consumer<Session> onAccept, onCancel;
}