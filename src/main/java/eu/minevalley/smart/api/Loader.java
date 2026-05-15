package eu.minevalley.smart.api;

import eu.minevalley.core.api.CoreModule;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface Loader {

    /**
     * Gets the smart app with the provided system name.
     *
     * @param systemName the system name of the smart app
     * @return the smart app with the provided system name or null if no such app exists
     */
    @Nullable
    @Contract(pure = true, value = "null -> null")
    SmartApp getSmartApp(@Nullable String systemName);

    /**
     * Gets all registered smart apps.
     *
     * @return list of all registered smart apps
     */
    @Nonnull
    @Contract(pure = true)
    List<SmartApp> getSmartApps();

    /**
     * Gets the underlying core module of the provided smart app.
     *
     * @param app app to get the underlying module of
     * @return the underlying module
     * @throws IllegalArgumentException if the app is null
     */
    @Nonnull
    @Contract(pure = true)
    CoreModule getUnderlyingModule(@Nonnull SmartApp app) throws IllegalArgumentException;
}
