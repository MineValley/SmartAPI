package minevalley.smart.api;

import minevalley.smart.api.enums.WindowSize;
import minevalley.smart.api.utils.window.WindowBuilder;
import minevalley.smart.api.utils.window.WindowItem;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface SmartProvider {

    String getUserSetting(String uniqueId, String key);

    void changeUserSetting(String uniqueId, String key, String newValue);

    WindowBuilder window(WindowSize size, boolean endSessionOnMinimize);

    WindowBuilder window(List<WindowItem> items, String title, Smart.PosItem... posItems);

    WindowItem windowItem(ItemStack itemStack, Consumer<Session> consumer);

    WindowItem advancedWindowItem(ItemStack itemStack, BiConsumer<Session, ClickType> consumer);

    List<Session> getAllSessions(SmartApp app);

    SmartApp getSmartApp(String systemName);

    List<SmartApp> getSmartApps();
}