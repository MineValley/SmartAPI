package minevalley.smart.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import minevalley.core.api.enums.InterfaceItem;
import minevalley.smart.api.enums.WindowSize;
import minevalley.smart.api.utils.window.WindowBuilder;
import minevalley.smart.api.utils.window.WindowItem;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

    /**
     * Creates new Window-builder.
     *
     * @return new Window-builder
     */
    public static WindowBuilder createWindow(boolean endSessionOnMinimize) {
        return provider.window(WindowSize.FULLSCREEN, endSessionOnMinimize);
    }

    /**
     * Creates new Window-builder.
     *
     * @return new Window-builder
     */
    public static WindowBuilder createWindow(WindowSize size, boolean endSessionOnMinimize) {
        return provider.window(size, endSessionOnMinimize);
    }

    /**
     * Creates new Window-builder with the specific size.
     *
     * @param items    list of the items to display
     * @param posItems items to be placed on specific slots on every inventory
     * @return new Window-builder
     */
    public static WindowBuilder createWindow(List<WindowItem> items, String title, PosItem... posItems) {
        return provider.window(items, title, posItems);
    }

    /**
     * Creates new Window-item, based on a specific itemstack with a specific callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param itemStack item which should be displayed in the inventory
     * @param consumer  consumer which gets called if a player clicks the item
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(ItemStack itemStack, Consumer<Session> consumer) {
        return provider.windowItem(itemStack, consumer);
    }

    /**
     * Creates new Window-item, based on a specific itemstack with a specific callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param item     item which should be displayed in the inventory
     * @param consumer consumer which gets called if a player clicks the item
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(InterfaceItem item, Consumer<Session> consumer) {
        return provider.windowItem(item.toItemStack(), consumer);
    }

    /**
     * Creates new Window-item, based on a specific itemstack with a specific callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param itemStack item which should be displayed in the inventory
     * @param consumer  consumer which gets called if a player clicks the item
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(ItemStack itemStack, BiConsumer<Session, ClickType> consumer) {
        return provider.advancedWindowItem(itemStack, consumer);
    }

    /**
     * Creates new Window-item, based on a specific itemstack with a specific callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param item     item which should be displayed in the inventory
     * @param consumer consumer which gets called if a player clicks the item
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(InterfaceItem item, BiConsumer<Session, ClickType> consumer) {
        return provider.advancedWindowItem(item.toItemStack(), consumer);
    }

    /**
     * Creates new Window-item, based on a specific itemstack without any callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param itemStack item which should be displayed in the inventory (use item-builder to create this item)
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(ItemStack itemStack) {
        return provider.windowItem(itemStack, null);
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


    @Getter
    @AllArgsConstructor
    public static class PosItem {
        private final int position;
        private final WindowItem WindowItem;
    }
}