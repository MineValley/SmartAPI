package minevalley.smart.api;

import minevalley.core.api.users.OnlineUser;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface SmartProvider {

    @Nonnull
    @Contract(pure = true)
    List<Session> getAllSessions(@Nonnull SmartApp app) throws IllegalArgumentException;

    @Nonnull
    @Contract(pure = true)
    List<Session> getAllSessions(@Nonnull OnlineUser user) throws IllegalArgumentException;

    @Nullable
    @Contract(pure = true, value = "null -> null")
    SmartApp getSmartApp(@Nullable String systemName);

    @Nonnull
    @Contract(pure = true)
    List<SmartApp> getSmartApps();

    Session startSession(@Nonnull SmartApp app, @Nonnull OnlineUser user) throws IllegalArgumentException;
}