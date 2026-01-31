package minevalley.smart.api;

import lombok.Getter;
import minevalley.core.api.users.OnlineUser;
import minevalley.smart.api.session.Session;

import javax.annotation.Nonnull;

@Getter
@SuppressWarnings("unused")
public abstract class SmartApp {

    private final App appDescription;

    public SmartApp() {
        final Class<? extends SmartApp> clazz = getClass();
        if (!clazz.isAnnotationPresent(App.class)) {
            throw new IllegalArgumentException("App annotation is missing in SmartApp '" + clazz.getSimpleName() + "'");
        }
        this.appDescription = clazz.getAnnotation(App.class);
    }

    /**
     * Is called whenever a new session is created by any user.
     *
     * @param session new created session
     */
    public abstract void onSessionCreate(@Nonnull Session session);

    /**
     * Starts a new session for the provided user.
     *
     * @param user the user to start the session for
     * @throws IllegalArgumentException if the provided user is null
     */
    public void startSession(@Nonnull OnlineUser user) throws IllegalArgumentException {
        Smart.startSession(this, user);
    }
}