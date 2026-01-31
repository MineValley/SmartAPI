package minevalley.smart.api;

import minevalley.core.api.users.OnlineUser;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unused")
public final class Smart {

    private static SmartProvider provider;

    @Nonnull
    @Contract(pure = true)
    public static List<Session> getAllSessions(@Nonnull SmartApp app) throws IllegalArgumentException {
        return provider.getAllSessions(app);
    }

    @Nonnull
    @Contract(pure = true)
    public static List<Session> getAllSessions(@Nonnull OnlineUser user) throws IllegalArgumentException {
        return provider.getAllSessions(user);
    }

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

    public static Session startSession(@Nonnull SmartApp app, @Nonnull OnlineUser user) throws IllegalArgumentException {
        return provider.startSession(app, user);
    }

    @Nullable
    @Contract(pure = true, value = "null -> null")
    public static SmartApp getSmartApp(@Nullable String systemName) {
        return provider.getSmartApp(systemName);
    }

    @Nonnull
    @Contract(pure = true)
    public static List<SmartApp> getSmartApps() {
        return provider.getSmartApps();
    }

}