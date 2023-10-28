package minevalley.smart.api;

import minevalley.smart.api.utils.window.Window;

import java.util.function.Consumer;

public interface Session {

    User getUser();

    void openWindow(Window window);

    Window getCurrentWindow();

    @Deprecated
    void close();

    void end();

    @Deprecated
    void onEnd(Consumer<Session> callback);

    void onEnd(Runnable runnable);

    void sendPurchaseOffer(PurchaseOffer offer);
}
