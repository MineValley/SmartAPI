package minevalley.smart.api;

import minevalley.core.api.CoreModule;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface SmartLoader {

    @Nullable
    @Contract(pure = true, value = "null -> null")
    SmartApp getSmartApp(@Nullable String systemName);

    @Nonnull
    @Contract(pure = true)
    List<SmartApp> getSmartApps();

    @Nonnull
    @Contract(pure = true)
    CoreModule getUnderlyingModule(@Nonnull SmartApp app) throws IllegalArgumentException;
}
