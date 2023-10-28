package minevalley.smart.api;

import minevalley.smart.api.utils.window.Window;

import java.util.function.Consumer;

public interface Session {

    @Deprecated
    User getUser();

    String getUserUniqueId();

    void openWindow(Window window);

    Window getCurrentWindow();

    @Deprecated
    void close();

    void end();

    @Deprecated
    void onEnd(Consumer<Session> callback);

    void onEnd(Runnable runnable);

    void sendPurchaseOffer(PurchaseOffer offer);

    default String getUserSetting(String key) {
        return Smart.getUserSetting(getUserUniqueId(), key);
    }

    default String getUserSetting(String key, String defaultValue) {
        return Smart.getUserSetting(getUserUniqueId(), defaultValue);
    }
}
