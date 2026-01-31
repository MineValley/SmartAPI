package minevalley.smart.api.session;

import minevalley.core.api.users.OnlineUser;
import minevalley.smart.api.SmartApp;
import minevalley.smart.api.payment.PurchaseOffer;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public interface Session {

    /**
     * Gets the SmartApp this session belongs to.
     *
     * @return the SmartApp
     */
    @Nonnull
    @Contract(pure = true)
    SmartApp getApp();

    /**
     * Gets the user this session belongs to.
     *
     * @return the user
     */
    @Nonnull
    @Contract(pure = true)
    OnlineUser getUser();

    /**
     * Ends this session.
     * <p>
     * <b>Note:</b>
     * <ul>
     *     <li>This method will also trigger any runnables registered via {@link #onEnd(Runnable)}.</li>
     *     <li>If the session has already ended, this method does nothing, the {@link #onEnd(Runnable)} will not be called again</li>
     *     <li>After calling this method, the session instance should be considered invalid and must not be used anymore.</li>
     * </ul>
     */
    void end();

    /**
     * Registers a runnable that will be executed when this session ends.
     * <p>
     * <b>Note:</b>
     * <ul>
     *     <li>If the session has already ended, the runnable will never be called!</li>
     *     <li>A session can only have one runnable that will be called on session end, so adding a new will replace an existing one</li>
     *     <li>The runnable will called whenever (and however) a session ended.</li>
     *     <li>Since sessions can only be ended once, this runnable will only be called once</li>
     * </ul>
     *
     * @param runnable runnable to be executed on session end
     * @throws IllegalArgumentException if the runnable is null (use {@code () -> {}} for an empty runnable)
     */
    @Contract(pure = true)
    void onEnd(@Nonnull Runnable runnable) throws IllegalArgumentException;

    /**
     * Sends a purchase offer to the user of this session.
     *
     * @param offer the purchase offer to be sent
     * @throws IllegalArgumentException if the offer is null
     */
    void sendPurchaseOffer(@Nonnull PurchaseOffer offer) throws IllegalArgumentException;
}
