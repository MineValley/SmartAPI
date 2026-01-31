package minevalley.smart.api;

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
}
