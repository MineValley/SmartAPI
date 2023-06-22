package minevalley.smart.api;

import minevalley.smart.api.utils.window.Window;

import java.util.function.Consumer;

public interface Session {

    User getUser();

    void openWindow(Window window);

    Window getCurrentWindow();

    void close();

    void onEnd(Consumer<Session> callback);

    void sendPurchaseOffer(PurchaseOffer offer);
}
