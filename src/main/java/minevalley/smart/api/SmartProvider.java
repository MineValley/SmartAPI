package minevalley.smart.api;

import java.util.List;

public interface SmartProvider {

    String getUserSetting(String uniqueId, String key);

    void changeUserSetting(String uniqueId, String key, String newValue);

    List<Session> getAllSessions(SmartApp app);

    SmartApp getSmartApp(String systemName);

    List<SmartApp> getSmartApps();
}