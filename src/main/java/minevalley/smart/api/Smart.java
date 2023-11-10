package minevalley.smart.api;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import minevalley.smart.api.enums.InterfaceItem;
import minevalley.smart.api.enums.WindowSize;
import minevalley.smart.api.utils.ItemBuilder;
import minevalley.smart.api.utils.window.WindowBuilder;
import minevalley.smart.api.utils.window.WindowItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class Smart {

    @Setter
    private static SmartServer server;

    public static void sendDebug(String message) {
        server.sendDebug(message);
    }

    public static int runSyncTaskNow(String taskName, Runnable runnable) {
        return runSyncTaskLater(taskName, 0, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a once off task to occur as soon as possible. This task will
     * be executed by a thread managed by the scheduler.
     *
     * @param taskName name of task
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int runAsyncTaskNow(String taskName, Runnable runnable) {
        return runAsyncTaskLater(taskName, 0, runnable);
    }

    /**
     * Schedules a once off task to occur after a delay.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param taskName name of task
     * @param delay    Delay in server ticks before executing task
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int runSyncTaskLater(String taskName, long delay, Runnable runnable) {
        return server.runSyncTaskLater(taskName, delay, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a once off task to occur after a delay. This task will be
     * executed by a thread managed by the scheduler.
     *
     * @param delay    Delay in server ticks before executing task
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int runAsyncTaskLater(String taskName, long delay, Runnable runnable) {
        return server.runAsyncTaskLater(taskName, delay, runnable);
    }

    /**
     * Schedules a repeating task.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param taskName name of task
     * @param period   Period in server ticks of the task
     * @param delay    Delay in server ticks before executing first repeat
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int runSyncTaskPeriodically(String taskName, long delay, long period, Runnable runnable) {
        return server.runSyncTaskPeriodically(taskName, delay, period, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a repeating task. This task will be executed by a thread
     * managed by the scheduler.
     *
     * @param taskName name of task
     * @param period   Period in server ticks of the task
     * @param delay    Delay in server ticks before executing first repeat
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     * task, but rather, "an async" task
     */
    public static int runAsyncTaskPeriodically(String taskName, long delay, long period, Runnable runnable) {
        return server.runAsyncTaskPeriodically(taskName, delay, period, runnable);
    }

    /**
     * Check if the task currently running.
     * <p>
     * A repeating task might not be running currently, but will be running in
     * the future. A task that has finished, and does not repeat, will not be
     * running ever again.
     * <p>
     * Explicitly, a task is running if there exists a thread for it, and that
     * thread is alive.
     *
     * @param taskId The task to check.
     * @return If the task is currently running.
     */
    public static boolean isCurrentlyRunning(int taskId) {
        return server.isCurrentlyRunning(taskId);
    }

    /**
     * Removes task from scheduler.
     *
     * @param taskId Id number of task to be removed
     */
    public static void cancelTask(int taskId) {
        server.cancelTask(taskId);
    }

    /**
     * Sets the setting with the given key.
     * <p>
     * <b>Note:</b> When using the settings-method, do never change the settings manually via database!
     *
     * @param key   string of the key
     * @param value string of the (new) value
     */
    public static void setSetting(String key, String value) {
        server.setSetting(key, value);
    }

    /**
     * Gets the settings with the specific key.
     *
     * @param key string of the key with which the setting is saved
     * @return setting as string from the specific key
     */
    public static String getSetting(String key) {
        return server.getSetting(key);
    }

    public static void changeUserSetting(String uniqueId, String key, String newValue) {
        server.changeUserSetting(uniqueId, key, newValue);
    }

    /**
     * Gets the settings with the specific key.
     *
     * @param key          string of the key with which the setting is saved
     * @param defaultValue value that is returned, if the setting is null
     * @return setting as string from the specific key
     */
    public static String getSetting(String key, String defaultValue) {
        return Optional.ofNullable(getSetting(key)).orElse(defaultValue);
    }

    public static String getUserSetting(String uniqueId, String key) {
        return server.getUserSetting(uniqueId, key);
    }

    public static String getUserSetting(String uniqueId, String key, String defaultValue) {
        return Optional.ofNullable(getUserSetting(uniqueId, key)).orElse(defaultValue);
    }

    /**
     * Updates a statistic with the given key.
     * <p>
     * <b>Note:</b> When using the stats-method, do never change the settings manually via database!
     *
     * @param key   string of the key
     * @param value double of the (new) value
     */
    public static void updateStatistic(String key, double value) {
        server.updateStatistic(key, value);
    }

    /**
     * Gets the statistic with the specific key.
     *
     * @param key string of the key with which the statistic is saved
     * @return statistic as double from the specific key
     */
    public static double getStatistic(String key) {
        return server.getStatistic(key);
    }

    /**
     * Gets the name of the player with the specific unique id
     *
     * @param uniqueId unique id of the player
     * @return name of the player
     */
    public static String getName(String uniqueId) {
        return server.getName(uniqueId);
    }

    /**
     * Gets the unique id of the player with the specific name
     *
     * @param name name of the player
     * @return unique id of the player
     */
    public static String getUniqueId(String name) {
        return server.getUniqueId(name);
    }

    /**
     * Gets a random integer with the given length.
     *
     * @param chars number of chars
     * @return random integer
     */
    public static int getRandomInteger(int chars) {
        return server.randomInteger(chars);
    }

    /**
     * Verifies that the given string is numeric and can be converted to an integer.
     * <p>
     * <b>Note:</b> This method also checks whether the integer exceeds the maximum value for integers.
     *
     * @param string string to verify
     * @return true, if the given string is numeric
     */
    public static boolean isInteger(String string) {
        return server.isInteger(string);
    }

    /**
     * Verifies that the given string is numeric and can be converted to a double.
     *
     * @param string string to verify
     * @return true, if the given string is numeric
     */
    public static boolean isDouble(String string) {
        return server.isDouble(string);
    }

    /**
     * Creates a readable string of the specific amountInCents of money.
     *
     * @param amountInCents amountInCents to convert
     * @return amountInCents as x.xxx.xxx,xx€
     */
    public static String formatMoney(int amountInCents) {
        return server.formatMoney(amountInCents);
    }

    public static String getFormattedDate(long time) {
        return new SimpleDateFormat("dd. MMMM yyyy - HH:mm", Locale.GERMANY).format(new Date(time)) + " Uhr";
    }

    public static String getCurrentTimeFormatted() {
        return getFormattedDate(System.currentTimeMillis());
    }

    /**
     * Creates new Window-builder.
     *
     * @return new Window-builder
     */
    public static WindowBuilder createWindow(boolean endSessionOnMinimize) {
        return server.window(WindowSize.FULLSCREEN, endSessionOnMinimize);
    }

    /**
     * Creates new Window-builder.
     *
     * @return new Window-builder
     */
    public static WindowBuilder createWindow(WindowSize size, boolean endSessionOnMinimize) {
        return server.window(size, endSessionOnMinimize);
    }

    /**
     * Creates new Window-builder with the specific size.
     *
     * @param items    list of the items to display
     * @param posItems items to be placed on specific slots on every inventory
     * @return new Window-builder
     */
    public static WindowBuilder createWindow(List<WindowItem> items, String title, PosItem... posItems) {
        return server.window(items, title, posItems);
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
        return server.windowItem(itemStack, consumer);
    }

    /**
     * Creates new Window-item, based on a specific itemstack with a specific callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param itemBuilder item which should be displayed in the inventory
     * @param consumer    consumer which gets called if a player clicks the item
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(ItemBuilder itemBuilder, Consumer<Session> consumer) {
        return server.windowItem(itemBuilder.build(), consumer);
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
        return server.windowItem(item.toItemStack(), consumer);
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
        return server.advancedWindowItem(itemStack, consumer);
    }

    /**
     * Creates new Window-item, based on a specific itemstack with a specific callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param itemBuilder item which should be displayed in the inventory
     * @param consumer    consumer which gets called if a player clicks the item
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(ItemBuilder itemBuilder, BiConsumer<Session, ClickType> consumer) {
        return server.advancedWindowItem(itemBuilder.build(), consumer);
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
        return server.advancedWindowItem(item.toItemStack(), consumer);
    }

    /**
     * Creates new Window-item, based on a specific itemstack without any callback.
     * Window-items can be added to inventory-Windows (built by Window-builder). If a player clicks the Window-item, the callback is called with the player-object.
     *
     * @param itemStack item which should be displayed in the inventory (use item-builder to create this item)
     * @return Window-item-object to add to the Window-builder
     */
    public static WindowItem createWindowItem(ItemStack itemStack) {
        return server.windowItem(itemStack, null);
    }

    /**
     * Creates a new item-builder based on a specific material.
     *
     * @param itemStack itemstack the item-builder will base on
     * @return new item-builder
     */
    public static ItemBuilder createItem(ItemStack itemStack) {
        return server.createItem(itemStack);
    }

    /**
     * Creates a new item-builder based on a specific material.
     *
     * @param material material of the item to create
     * @return new item-builder
     */
    public static ItemBuilder createItem(Material material) {
        return server.createItem(material);
    }

    /**
     * Creates a new item-builder based on a specific material and data.
     *
     * @param material material of the item to create
     * @param data     specific data for the item as short
     * @return new item-builder
     */
    public static ItemBuilder createItem(Material material, int data) {
        return server.createItem(material, data);
    }

    /**
     * Creates a new item-builder from the players head.
     *
     * @param player player whose head is wanted
     * @return new item-builder
     */
    public static ItemBuilder createItem(Player player) {
        return server.createItem(player);
    }

    /**
     * Creates a new item-builder from the players head based on its unique id.
     *
     * @param uniqueId unique id of the player whose head is wanted
     * @return new item-builder
     */
    public static ItemBuilder createItem(UUID uniqueId) {
        return server.createItem(uniqueId);
    }

    /**
     * Creates new item-builder out of a custom head, based on its link.
     * <p>
     * Example - head from <a href="https://minecraft-heads.com/custom-heads">head-database</a>:
     * The url to the head is:
     * 68c2f1f7e8cd6b00d30f0edaeefce38e889173c30c701fac0da860e0a2125ec8
     * <p>
     * You can use this url to get the head. It doesn't matter whether you're using the whole link (starting with "textures.minecraft.net") or just using the number, as shown above.
     * <p>
     * Note: Always cache heads you already created! Getting/creating new heads can be a waste of server-performance. A simple way to cash all heads used in inventories, is to load the with the onEnable()-method.
     *
     * @param url link to <span style="text-decoration:underline;">or</span></span> the id of the specific head
     * @return item-builder based on the chosen head
     */
    public static ItemBuilder createItem(String url) {
        return server.createItem(url);
    }

    /**
     * Gets whether the server is currently in maintenance.
     *
     * @return true, if the server is in maintenance
     */
    public static boolean isMaintenance() {
        return server.isMaintenance();
    }

    public static SmartServer getServerInstance() {
        return server;
    }

    public static List<Session> getAllSessions(SmartApp app) {
        return server.getAllSessions(app);
    }

    public static SmartApp getSmartApp(String systemName) {
        return server.getSmartApp(systemName);
    }

    /**
     * Creates a {@link Gson} instance that fits in a page for pretty printing.
     * Use this, to convert json-strings to specific objects and vice versa.
     *
     * @return an instance of Gson configured that fits in a page for pretty printing
     */
    public static Gson getGson() {
        return server.getGson();
    }

    @Getter
    @AllArgsConstructor
    public static class PosItem {
        private final int position;
        private final WindowItem WindowItem;
    }
}