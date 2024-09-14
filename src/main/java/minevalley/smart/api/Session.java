package minevalley.smart.api;

import minevalley.core.api.users.OnlineUser;
import minevalley.smart.api.utils.window.Window;

@SuppressWarnings("unused")
public interface Session {

    OnlineUser getUser();

    void end();

    void onEnd(Runnable runnable);

    void openWindow(Window window);

    Window getCurrentWindow();

    void sendPurchaseOffer(PurchaseOffer offer);
}
