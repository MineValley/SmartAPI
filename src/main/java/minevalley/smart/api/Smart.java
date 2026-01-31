package minevalley.smart.api;

import lombok.Setter;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unused")
public final class Smart {

    @Setter
    private static SmartProvider provider;

    @Nonnull
    @Contract(pure = true)
    public static List<Session> getAllSessions(@Nonnull SmartApp app) throws IllegalArgumentException {
        return provider.getAllSessions(app);
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