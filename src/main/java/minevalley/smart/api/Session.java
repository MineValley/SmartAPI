package minevalley.smart.api;

import minevalley.core.api.users.OnlineUser;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public interface Session {

    @Nonnull
    @Contract(pure = true)
    SmartApp getApp();

    @Nonnull
    @Contract(pure = true)
    OnlineUser getUser();

    void end();

    @Contract(pure = true)
    void onEnd(@Nonnull Runnable runnable) throws IllegalArgumentException;

    void sendPurchaseOffer(@Nonnull PurchaseOffer offer) throws IllegalArgumentException;
}
