package eu.minevalley.smart.api;

import eu.minevalley.core.api.user.OnlineUser;
import eu.minevalley.smart.api.session.Session;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import java.util.List;

@SuppressWarnings("unused")
public interface Smart {

    /**
     * Gets all active sessions for the provided app.
     *
     * @param app the app to get sessions for
     * @return list of all active sessions for the provided app
     * @throws IllegalArgumentException if the provided app is null
     */
    @Nonnull
    @Contract(pure = true)
    List<Session> getAllSessions(@Nonnull SmartApp app) throws IllegalArgumentException;

    /**
     * Gets all active sessions for the provided user.
     *
     * @param user the user to get sessions for
     * @return list of all active sessions for the provided user
     * @throws IllegalArgumentException if the provided user is null
     */
    @Nonnull
    @Contract(pure = true)
    List<Session> getAllSessions(@Nonnull OnlineUser user) throws IllegalArgumentException;

    /**
     * Gets an active session for the provided app and user or creates a new one if none exists.
     *
     * @param app  app to get or create the session for
     * @param user user to get or create the session for
     * @return the active (or newly created) session for the provided app and user
     * @throws IllegalArgumentException if the provided app or user is null
     */
    default Session getOrCreateSession(@Nonnull SmartApp app, @Nonnull OnlineUser user)
            throws IllegalArgumentException {
        if (app == null) {
            throw new IllegalArgumentException("app cannot be null");
        }
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        return getAllSessions(user).stream()
                .filter(session -> session.getApp().equals(app))
                .findAny()
                .orElse(startSession(app, user));
    }

    /**
     * Starts a new session for the provided app and user.
     *
     * @param app  app to start the session for
     * @param user user to start the session for
     * @return the newly started session for the provided app and user
     * @throws IllegalArgumentException if the provided app or user is null
     */
    @Nonnull
    @Contract("_, _ -> new")
    Session startSession(@Nonnull SmartApp app, @Nonnull OnlineUser user) throws IllegalArgumentException;
}