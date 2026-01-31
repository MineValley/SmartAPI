package minevalley.smart.api;

import lombok.Setter;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public final class Smart {

    @Setter
    private static SmartProvider provider;

    public static void changeUserSetting(String uniqueId, String key, String newValue) {
        provider.changeUserSetting(uniqueId, key, newValue);
    }

    public static String getUserSetting(String uniqueId, String key) {
        return provider.getUserSetting(uniqueId, key);
    }

    public static String getUserSetting(String uniqueId, String key, String defaultValue) {
        return Optional.ofNullable(getUserSetting(uniqueId, key)).orElse(defaultValue);
    }

    public static List<Session> getAllSessions(SmartApp app) {
        return provider.getAllSessions(app);
    }

    public static SmartApp getSmartApp(String systemName) {
        return provider.getSmartApp(systemName);
    }

    public static List<SmartApp> getSmartApps() {
        return provider.getSmartApps();
    }

}