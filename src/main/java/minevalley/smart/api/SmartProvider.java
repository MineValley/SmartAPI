package minevalley.smart.api;

import minevalley.core.api.CoreModule;
import minevalley.core.api.users.OnlineUser;
import minevalley.smart.api.session.Session;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import java.util.List;

public interface SmartProvider {

    @Nonnull
    @Contract(pure = true)
    List<Session> getAllSessions(@Nonnull SmartApp app) throws IllegalArgumentException;

    @Nonnull
    @Contract(pure = true)
    List<Session> getAllSessions(@Nonnull OnlineUser user) throws IllegalArgumentException;

    @Nonnull
    Session startSession(@Nonnull SmartApp app, @Nonnull OnlineUser user) throws IllegalArgumentException;

    @Nonnull
    @Contract(pure = true)
    CoreModule getUnderlyingModule(@Nonnull SmartApp app) throws IllegalArgumentException;
}