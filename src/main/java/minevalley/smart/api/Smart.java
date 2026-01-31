package minevalley.smart.api;

import lombok.Setter;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public final class Smart {

    @Setter
    private static SmartProvider provider;

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