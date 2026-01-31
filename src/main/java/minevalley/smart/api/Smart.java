package minevalley.smart.api;

import minevalley.core.api.CoreModule;
import minevalley.core.api.users.OnlineUser;
import minevalley.smart.api.session.Session;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unused")
public final class Smart {

    private static SmartProvider provider;
    private static SmartLoader loader;

    /**
     * Gets all active sessions for the provided app.
     *
     * @param app the app to get sessions for
     * @return list of all active sessions for the provided app
     * @throws IllegalArgumentException if the provided app is null
     */
    @Nonnull
    @Contract(pure = true)
    public static List<Session> getAllSessions(@Nonnull SmartApp app) throws IllegalArgumentException {
        return provider.getAllSessions(app);
    }

    /**
     * Gets all active sessions for the provided user.
     *
     * @param user the user to get sessions for
     * @return list of all active sessions for the provided user
     * @throws IllegalArgumentException if the provided user is null
     */
    @Nonnull
    @Contract(pure = true)
    public static List<Session> getAllSessions(@Nonnull OnlineUser user) throws IllegalArgumentException {
        return provider.getAllSessions(user);
    }

    /**
     * Gets an active session for the provided app and user or creates a new one if none exists.
     *
     * @param app  app to get or create the session for
     * @param user user to get or create the session for
     * @return the active (or newly created) session for the provided app and user
     * @throws IllegalArgumentException if the provided app or user is null
     */
    public static Session getOrCreateSession(@Nonnull SmartApp app, @Nonnull OnlineUser user)
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
    public static Session startSession(@Nonnull SmartApp app, @Nonnull OnlineUser user) throws IllegalArgumentException {
        return provider.startSession(app, user);
    }

    /**
     * Gets the smart app with the provided system name.
     *
     * @param systemName the system name of the smart app
     * @return the smart app with the provided system name or null if no such app exists
     */
    @Nullable
    @Contract(pure = true, value = "null -> null")
    public static SmartApp getSmartApp(@Nullable String systemName) {
        return loader.getSmartApp(systemName);
    }

    /**
     * Gets all registered smart apps.
     *
     * @return list of all registered smart apps
     */
    @Nonnull
    @Contract(pure = true)
    public static List<SmartApp> getSmartApps() {
        return loader.getSmartApps();
    }

    @Nonnull
    @Contract(pure = true)
    public static CoreModule getUnderlyingModule(@Nonnull SmartApp app) throws IllegalArgumentException {
        return provider.getUnderlyingModule(app);
    }
}