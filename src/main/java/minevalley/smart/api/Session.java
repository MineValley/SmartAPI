package minevalley.smart.api;

import minevalley.core.api.smart.AppSession;
import minevalley.smart.api.utils.window.Window;

public interface Session extends AppSession {

    void openWindow(Window window);

    Window getCurrentWindow();

    void sendPurchaseOffer(PurchaseOffer offer);

    default String getUserSetting(String key) {
        return Smart.getUserSetting(getUser().getUniqueId(), key);
    }

    default String getUserSetting(String key, String defaultValue) {
        return Smart.getUserSetting(getUser().getUniqueId(), key, defaultValue);
    }

    default void changeUserSetting(String key, String newValue) {
        Smart.changeUserSetting(getUser().getUniqueId(), key, newValue);
    }
}
