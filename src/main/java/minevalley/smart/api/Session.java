package minevalley.smart.api;

import minevalley.core.api.users.OnlineUser;

@SuppressWarnings("unused")
public interface Session {

    OnlineUser getUser();

    void end();

    void onEnd(Runnable runnable);

    void sendPurchaseOffer(PurchaseOffer offer);
}
