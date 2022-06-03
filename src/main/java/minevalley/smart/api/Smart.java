package minevalley.smart.api;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import minevalley.smart.api.corporations.Department;
import minevalley.smart.api.corporations.Group;
import minevalley.smart.api.database.DatabaseEntry;
import minevalley.smart.api.database.DatabaseEntryCollection;
import minevalley.smart.api.database.DatabaseTable;
import minevalley.smart.api.database.Value;
import minevalley.smart.api.economy.BankAccount;
import minevalley.smart.api.enums.InterfaceItem;
import minevalley.smart.api.modulepipeline.Container;
import minevalley.smart.api.modulepipeline.PipelineReceiver;
import minevalley.smart.api.phone.Telephone;
import minevalley.smart.api.timing.Reminder;
import minevalley.smart.api.timing.RepeatingTimer;
import minevalley.smart.api.timing.Timer;
import minevalley.smart.api.utils.*;
import minevalley.smart.api.utils.gui.WindowBuilder;
import minevalley.smart.api.utils.gui.WindowItem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class Smart {

    private static SmartServer server;

    public Smart(SmartServer server) {
        Smart.server = server;
    }

    /**
     * @param period   Period in server ticks of the task
     * @param delay    Delay in server ticks before executing first repeat
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int scheduleSyncRepeatingTask(long delay, long period, BukkitRunnable runnable) {
        return server.scheduleSyncRepeatingTask(delay, period, runnable);
    }

    /**
     * Schedules a repeating task.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param period   Period in server ticks of the task
     * @param delay    Delay in server ticks before executing first repeat
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int scheduleSyncRepeatingTask(long delay, long period, Runnable runnable) {
        return server.scheduleSyncRepeatingTask(delay, period, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a repeating task. This task will be executed by a thread
     * managed by the scheduler.
     *
     * @param period   Period in server ticks of the task
     * @param delay    Delay in server ticks before executing first repeat
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     * @deprecated This name is misleading, as it does not schedule "a sync"
     * task, but rather, "an async" task
     */
    public static int scheduleAsyncRepeatingTask(long delay, long period, Runnable runnable) {
        return server.scheduleAsyncRepeatingTask(delay, period, runnable);
    }

    /**
     * @param delay    Delay in server ticks before executing task
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int scheduleSyncDelayedTask(long delay, BukkitRunnable runnable) {
        return server.scheduleSyncDelayedTask(delay, runnable);
    }

    /**
     * Schedules a once off task to occur after a delay.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param delay    Delay in server ticks before executing task
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int scheduleSyncDelayedTask(long delay, Runnable runnable) {
        return server.scheduleSyncDelayedTask(delay, runnable);
    }

    /**
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int scheduleSyncDelayedTask(BukkitRunnable runnable) {
        return server.scheduleSyncDelayedTask(runnable);
    }

    /**
     * Schedules a once off task to occur as soon as possible.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    public static int scheduleSyncDelayedTask(Runnable runnable) {
        return server.scheduleSyncDelayedTask(runnable);
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
     * @deprecated This name is misleading, as it does not schedule "a sync"
     * task, but rather, "an async" task
     */
    public static int scheduleAsyncDelayedTask(long delay, Runnable runnable) {
        return server.scheduleAsyncDelayedTask(delay, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a once off task to occur as soon as possible. This task will
     * be executed by a thread managed by the scheduler.
     *
     * @param runnable Task to be executed
     * @return Task id number (-1 if scheduling failed)
     * @deprecated This name is misleading, as it does not schedule "a sync"
     * task, but rather, "an async" task
     */
    public static int scheduleAsyncDelayedTask(Runnable runnable) {
        return server.scheduleAsyncDelayedTask(runnable);
    }

    /**
     * Returns a task that will run on the next server tick.
     *
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTask(Runnable runnable) {
        return server.runTask(runnable);
    }


    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will run asynchronously.
     *
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskAsync(Runnable runnable) {
        return server.runTaskAsync(runnable);
    }

    /**
     * @param runnable the task to be run
     * @param delay    the ticks to wait before running the task
     * @param period   the ticks to wait between runs
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskTimer(long delay, long period, BukkitRunnable runnable) {
        return server.runTaskTimer(delay, period, runnable);
    }

    /**
     * Returns a task that will repeatedly run until cancelled, starting after
     * the specified number of server ticks.
     *
     * @param period   the ticks to wait between runs
     * @param delay    the ticks to wait before running the task
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskTimer(long delay, long period, Runnable runnable) {
        return server.runTaskTimer(delay, period, runnable);
    }

    /**
     * @param period   the ticks to wait between runs
     * @param delay    the ticks to wait before running the task for the first time
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskTimerAsync(long delay, long period, BukkitRunnable runnable) {
        return server.runTaskTimerAsync(delay, period, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     *
     * @param period   the ticks to wait between runs
     * @param delay    the ticks to wait before running the task for the first time
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskTimerAsync(long delay, long period, Runnable runnable) {
        return server.runTaskTimerAsync(delay, period, runnable);
    }

    /**
     * @param delay    the ticks to wait before running the task
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskLater(long delay, BukkitRunnable runnable) {
        return server.runTaskLater(delay, runnable);
    }

    /**
     * Returns a task that will run after the specified number of server
     * ticks.
     *
     * @param delay    the ticks to wait before running the task
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskLater(long delay, Runnable runnable) {
        return server.runTaskLater(delay, runnable);
    }

    /**
     * @param delay    the ticks to wait before running the task
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskLaterAsync(long delay, BukkitRunnable runnable) {
        return server.runTaskLaterAsync(delay, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will run asynchronously after the specified number
     * of server ticks.
     *
     * @param delay    the ticks to wait before running the task
     * @param runnable the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public static BukkitTask runTaskLaterAsync(long delay, Runnable runnable) {
        return server.runTaskLaterAsync(delay, runnable);
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
     *               <p>
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

    public static JavaPlugin getInstance() {
        return server.getInstance();
    }

    public static void registerEvent(Class<? extends Event> cls, EventListener listener) {
        server.registerEvent(cls, listener);
    }

    public static void unregisterEvent(Class<? extends Event> cls, EventListener listener) {
        server.unregisterEvent(cls, listener);
    }

    public static void registerListeners(Listener listener) {
        server.registerListeners(listener);
    }

    public static void registerPipeline(PipelineReceiver pipelineReceiver) {
        server.registerPipelineReceiver(pipelineReceiver);
    }

    public static void sendPipelineContainer(String pipelineName, Container container) {
        server.sendPipelineContainer(pipelineName, container);
    }

    /**
     * Sends a message to all online team-members
     *
     * @param message message as string
     */
    public static void sendTeamChatMessage(String message) {
        server.sendTeamChatMessage(message);
    }

    /**
     * Sends a message to all online team-members
     *
     * @param message message as base-component (useful for clickable messages)
     */
    public static void sendTeamChatMessage(BaseComponent[] message) {
        server.sendTeamChatMessage(message);
    }

    /**
     * Gets the specific database-entry from the given ResultSet, the tablename and the entrys index.
     *
     * @param tableName name of the table as string
     * @param resultSet resultset
     * @param index     index of the entry
     * @return DatabaseEntry from the given parameters
     */
    public static DatabaseEntry getDatabaseEntry(String tableName, ResultSet resultSet, int index) {
        return server.databaseEntry(tableName, resultSet, index);
    }

    /**
     * Gets the specific database-entry from the specified table with the specified value in the column.
     * If there are more than one entries, that math the given description, this gets the first one.
     * If you want to get multiple entries, use database-collection, or database-table!
     *
     * @param tableName   name of the table as string
     * @param searchValue value according to which the entries are filtered in a specific column
     * @return the first database-entry that matches the given description
     */
    public static DatabaseEntry getDatabaseEntry(String tableName, Value searchValue) {
        return server.databaseEntry(tableName, searchValue);
    }

    /**
     * Creates a database-collection with the given entries.
     *
     * @param tableName name of the table as string
     * @param entries   list of entries
     * @return collection of the given entries
     */
    public static DatabaseEntryCollection getDatabaseEntryCollection(String tableName, List<DatabaseEntry> entries) {
        return server.databaseEntryCollection(tableName, entries);
    }

    /**
     * Gets a database-collection from the specified table with the specified value in the column.
     * This gets all of the entries that match the description. If you're searching for one single entry, use database-entry!
     *
     * @param tableName   name of the table as string
     * @param searchValue value according to which the entries are filtered in a specific column
     * @return a collection of all database-entries in this table, that matches the given description
     */
    public static DatabaseEntryCollection getDatabaseEntryCollection(String tableName, Value searchValue) {
        return server.databaseEntryCollection(tableName, searchValue);
    }

    /**
     * Gets the database-table with the specific name.
     *
     * @param tableName name of the database-table
     * @return database-table with specific name
     */
    public static DatabaseTable getDatabaseTable(String tableName) {
        return server.databaseTable(tableName);
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


    /**
     * Gets the settings with the specific key.
     *
     * @param key          string of the key with which the setting is saved
     * @param defaultValue value that is returned, if the setting is null
     * @return setting as string from the specific key
     */
    public static String getSetting(String key, String defaultValue) {
        final String result = server.getSetting(key);
        return result == null ? defaultValue : result;
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
     * Adds this object as a metadata to the metadatable object.
     *
     * @param metadatable object to which the metadata should be added
     * @param key         key under which the metadata can be found
     * @param value       object to be added as metadata
     */
    public static void setMetadata(Metadatable metadatable, String key, Object value) {
        server.setMetadata(metadatable, key, value);
    }

    /**
     * Removes all metadata-objects with the given key from the specifiy metadatable object.
     *
     * @param metadatable object from which the metadata should be removed
     * @param key         object
     */
    public static void removeMetadata(Metadatable metadatable, String key) {
        server.removeMetadata(metadatable, key);
    }

    /**
     * Gets a list of all metadata-values in link to the object.
     *
     * @param metadatable object to get linked data from
     * @param key         key of the metadata
     * @return list of all metadata-values
     */
    public static List<MetadataValue> getMetadata(Metadatable metadatable, String key) {
        return server.getMetadata(metadatable, key);
    }

    /**
     * Gets a players user object
     *
     * @param player player object to get user from
     * @return user object of the given player
     */
    public static User getUser(Player player) {
        return server.getUser(player);
    }

    /**
     * Gets the name of the player with the specifc unique id
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
     * Removes the color-codes from a given string
     *
     * @param text string which could contain color-codes
     * @return string without any color-codes
     */
    public static String removeColorCodes(String text) {
        return server.removeColorCodes(text);
    }

    /**
     * Converts chat-color-codes to normal color-codes (& -> §)
     *
     * @param text string which could contain chat-color-codes
     * @return string with converted color-codes
     */
    public static String convertColorCodes(String text) {
        return server.convertColorCodes(text);
    }

    /**
     * Checks whether the given string contains a forbidden word.
     *
     * @param text text to check
     * @return true, if string contains forbidden words
     */
    public static boolean containsForbiddenWords(String text) {
        return server.containsForForbiddenWords(text);
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

    /**
     * Gets an random integer with the given length.
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
    public static boolean isNumeric(String string) {
        return server.isNumeric(string);
    }

    /**
     * Creates a readable string of the specific amount of money.
     *
     * @param amount amount to convert
     * @return amount as x.xxx.xxx,xx€
     */
    public static String formatMoney(double amount) {
        return server.formatMoney(amount);
    }

    public static String getFormattedDate(long time) {
        return new SimpleDateFormat("dd. MMMM yyyy - HH:mm", Locale.GERMANY).format(new Date(time)) + " Uhr";
    }

    public static String getCurrentTimeFormatted() {
        return getFormattedDate(System.currentTimeMillis());
    }

    /**
     * Creates new gui-builder based on a existing inventory.
     *
     * @param inventory inventory on which the gui-builder should base on
     * @return new gui-builder
     */
    public static WindowBuilder createGui(Inventory inventory) {
        return server.gui(inventory);
    }

    /**
     * Creates new gui-builder with the specific size.
     *
     * @param size size of the inventory (has to be a multiple of 9)
     * @return new gui-builder
     */
    public static WindowBuilder createGui(int size) {
        return server.gui(size);
    }

    /**
     * Creates new gui-builder with the specific size.
     *
     * @param items    list of the items to display
     * @param size     size of each inventory (has to be a multiple of 9)
     * @param posItems items to be placed on specific slots on every inventory
     * @return new gui-builder
     */
    public static WindowBuilder createGui(List<WindowItem> items, int size, String title, PosItem... posItems) {
        return server.gui(items, size, title, posItems);
    }

    /**
     * Creates new gui-item, based on a specific itemstack with a specific callback.
     * Gui-items can be added to inventory-guis (built by gui-builder). If a player clicks the gui-item, the callback is called with the player-object.
     *
     * @param itemStack item which should be displayed in the inventory
     * @param consumer  consumer which gets calles if a player clicks the item
     * @return gui-item-object to add to the gui-builder
     */
    public static WindowItem createGuiItem(ItemStack itemStack, Consumer<User> consumer) {
        return server.guiItem(itemStack, consumer);
    }

    /**
     * Creates new gui-item, based on a specific itemstack with a specific callback.
     * Gui-items can be added to inventory-guis (built by gui-builder). If a player clicks the gui-item, the callback is called with the player-object.
     *
     * @param itemBuilder item which should be displayed in the inventory
     * @param consumer    consumer which gets calles if a player clicks the item
     * @return gui-item-object to add to the gui-builder
     */
    public static WindowItem createGuiItem(ItemBuilder itemBuilder, Consumer<User> consumer) {
        return server.guiItem(itemBuilder.build(), consumer);
    }

    /**
     * Creates new gui-item, based on a specific itemstack with a specific callback.
     * Gui-items can be added to inventory-guis (built by gui-builder). If a player clicks the gui-item, the callback is called with the player-object.
     *
     * @param item     item which should be displayed in the inventory
     * @param consumer consumer which gets calles if a player clicks the item
     * @return gui-item-object to add to the gui-builder
     */
    public static WindowItem createGuiItem(InterfaceItem item, Consumer<User> consumer) {
        return server.guiItem(item.toItemStack(), consumer);
    }

    /**
     * Creates new gui-item, based on a specific itemstack with a specific callback.
     * Gui-items can be added to inventory-guis (built by gui-builder). If a player clicks the gui-item, the callback is called with the player-object.
     *
     * @param itemStack item which should be displayed in the inventory
     * @param consumer  consumer which gets calles if a player clicks the item
     * @return gui-item-object to add to the gui-builder
     */
    public static WindowItem createGuiItem(ItemStack itemStack, BiConsumer<User, ClickType> consumer) {
        return server.advancedGuiItem(itemStack, consumer);
    }

    /**
     * Creates new gui-item, based on a specific itemstack with a specific callback.
     * Gui-items can be added to inventory-guis (built by gui-builder). If a player clicks the gui-item, the callback is called with the player-object.
     *
     * @param itemBuilder item which should be displayed in the inventory
     * @param consumer    consumer which gets calles if a player clicks the item
     * @return gui-item-object to add to the gui-builder
     */
    public static WindowItem createGuiItem(ItemBuilder itemBuilder, BiConsumer<User, ClickType> consumer) {
        return server.advancedGuiItem(itemBuilder.build(), consumer);
    }

    /**
     * Creates new gui-item, based on a specific itemstack with a specific callback.
     * Gui-items can be added to inventory-guis (built by gui-builder). If a player clicks the gui-item, the callback is called with the player-object.
     *
     * @param item     item which should be displayed in the inventory
     * @param consumer consumer which gets calles if a player clicks the item
     * @return gui-item-object to add to the gui-builder
     */
    public static WindowItem createGuiItem(InterfaceItem item, BiConsumer<User, ClickType> consumer) {
        return server.advancedGuiItem(item.toItemStack(), consumer);
    }

    /**
     * Creates new gui-item, based on a specific itemstack without any callback.
     * Gui-items can be added to inventory-guis (built by gui-builder). If a player clicks the gui-item, the callback is called with the player-object.
     *
     * @param itemStack item which should be displayed in the inventory (use item-builder to create this item)
     * @return gui-item-object to add to the gui-builder
     */
    public static WindowItem createGuiItem(ItemStack itemStack) {
        return server.guiItem(itemStack, null);
    }

    /**
     * Creates new countdown-object.
     * Note: Don't use this method to realize cooldowns for player-actions. Use schedulers instead!
     *
     * @return new countdown-object
     */
    public static Countdown createCountdown() {
        return server.createCountdown();
    }

    /**
     * Starts the specific countdown.
     *
     * @param countdown countdown to start
     */
    public static void startCountdown(Countdown countdown) {
        server.startCountdown(countdown);
    }

    /**
     * Stops the specific countdown.
     *
     * @param countdown countdown to stop
     */
    public static void stopCountdown(Countdown countdown) {
        server.stopCountdown(countdown);
    }

    /**
     * Gets the servers main map.
     *
     * @return main map
     */
    public static World getMainWorld() {
        return Bukkit.getWorld("world");
    }

    /**
     * Gets the building map.
     *
     * @return building map
     */
    public static World getBuildingWorld() {
        return Bukkit.getWorld("bauteam");
    }

    /**
     * Gets the shadow map.
     *
     * @return shadow map
     */
    public static World getShadowMap() {
        return Bukkit.getWorld("shadow");
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
     * Creates the often used gray hover text to display a clickable message in chat.
     *
     * @param text text to be displayed
     * @return HoverEvent object to be put into .event()
     */
    public static HoverEvent createHoverText(String text) {
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(text).color(ChatColor.GRAY).create());
    }

    /**
     * Creates a click event consisting of a clickable message.
     *
     * @param selfCancelling defines whether the message is clickable multiple times
     * @param callback       is called if the player clicks the message
     * @return ClickEvent object to be put into .event()
     */
    public static ClickEvent createClickEvent(boolean selfCancelling, Consumer<User> callback) {
        return server.createClickEvent(selfCancelling, callback);
    }

    /**
     * Creates a click event consisting of a clickable message.
     * <p>
     * <b>Note:</b> This clickable message is only clickable once! Use createClickEvent(boolean, Consumer) if you want to create it for multiple use.
     *
     * @param callback is called if the player clicks the message
     * @return ClickEvent object to be put into .event()
     */
    public static ClickEvent createClickEvent(Consumer<User> callback) {
        return server.createClickEvent(true, callback);
    }

    /**
     * Creates a clickable message without any specifications.
     *
     * @return new clickable message
     */
    public static ClickableMessage createClickableMessage() {
        return server.createClickableMessage();
    }

    /**
     * Creates a clickable message that executes the given callback.
     *
     * @param callback callback to be executed if player clicks the message
     * @return new clickable message
     */
    public static ClickableMessage createClickableMessage(Consumer<User> callback) {
        return server.createClickableMessage(callback);
    }

    /**
     * Creates a clickable message that executes the given callback, and is only clickable once (if selfCanselling is true).
     *
     * @param callback       callbacl to be executed if player clicks the message
     * @param selfCancelling defines if the message is clickable multiple times
     * @return new clickable message
     */
    public static ClickableMessage createClickableMessage(Consumer<User> callback, boolean selfCancelling) {
        return server.createClickableMessage(callback, selfCancelling);
    }

    /**
     * Creates a menu which can be send to users chat.
     *
     * @param options clickable options (message and clickable)
     * @return new chat menu
     */
    public static ChatMenu createChatMenu(ChatMenu.Option... options) {
        return server.createChatMenu(options);
    }

    /**
     * Creates a menu which can be send to users chat.
     *
     * @return new chat menu
     */
    public static ChatMenu createChatMenu() {
        return server.createChatMenu();
    }

    /**
     * Converts an inventory that is converted to a string back into the usual inventory object.
     *
     * @param inventory string to convert to inventory
     * @return inventory object
     */
    public static Inventory getInventoryFromString(String inventory) {
        return server.getInventoryFromString(inventory);
    }

    /**
     * Converts an inventory and its contents into a string. This method is used to save inventories to the database.
     *
     * @param inventory inventory to convert
     * @return inventory from string
     */
    public static String getStringFromInventory(Inventory inventory) {
        return server.getStringFromInventory(inventory);
    }

    /**
     * Gets the bank account with the specific iban.
     *
     * @param iban iban as string
     * @return bank account
     */
    public static BankAccount getBankAccount(String iban) {
        return server.getBankAccount(iban);
    }

    /**
     * Creates a new bank account with the given holder.
     *
     * @param holder holder of this bank account
     * @return new bank account
     */
    public static BankAccount createBankAccount(Registered holder) {
        return server.createBankAccount(holder);
    }

    /**
     * Gets the group (organization/company) with the specific id.
     *
     * @param id id as integer
     * @return group with the specific id
     */
    public static Group getGroup(int id) {
        return server.getGroup(id);
    }

    /**
     * Gets the group (organization/company) with the specific name.
     *
     * @param name name as string
     * @return group with the specifc name
     */
    public static Group getGroup(String name) {
        return server.getGroup(name);
    }

    /**
     * Creates a new telephone with the given owner. The owner should be the registered id.
     *
     * @param owner owner's registered id
     * @return new telephone with the specific owner
     */
    public static Telephone createTelephone(String owner) {
        return server.createTelephone(owner);
    }

    /**
     * Gets the telephone with the specific telephone number.
     *
     * @param telephoneNumber telephone number to get telephone from
     * @return telephone with the specific telephone number
     */
    public static Telephone getTelephone(String telephoneNumber) {
        return server.getTelephone(telephoneNumber);
    }

    /**
     * Gets the registered object of a user.
     *
     * @param user user to get registered object from
     * @return registered object of user
     */
    public static Registered getRegistered(User user) {
        return server.getRegistered(user);
    }

    /**
     * Gets the registered object of a group.
     *
     * @param group group to get registered object from
     * @return registered object of group
     */
    public static Registered getRegistered(Group group) {
        return server.getRegistered(group);
    }

    /**
     * Gets the registered object of a department.
     *
     * @param department department to get registered object from
     * @return registered object of department
     */
    public static Registered getRegistered(Department department) {
        return server.getRegistered(department);
    }

    /**
     * Gets the registered object that is represented by the specific string.
     *
     * @param rawRegistered registered as string
     * @return represented registered
     */
    public static Registered getRegistered(String rawRegistered) {
        return server.getRegistered(rawRegistered);
    }

    /**
     * Starts a timer with the specific parameters.
     *
     * @param delay    delay after which this timer terminates
     * @param callback callback that is called when this timer terminates
     * @return timer with the specific parameters
     */
    public static Timer startTimer(int delay, @NonNull Runnable callback) {
        return server.startTimer(delay, callback);
    }

    /**
     * Starts a repeating timer with the specific parameters.
     *
     * @param delay    seconds to wait before the first period
     * @param period   period in which this repeating timer is called
     * @param callback callback that is called when this repeating timer reaches a period
     * @return repeating timer with the specific parameters.
     */
    public static RepeatingTimer startRepeatingTimer(int delay, int period, @NonNull Runnable callback) {
        return server.startRepeatingTimer(delay, period, callback);
    }

    /**
     * Creates a reminder with the specific parameters.
     *
     * @param hours    hours on which this reminder is called
     * @param minutes  minutes on which this reminder is called
     * @param callback callback that is called when the given date/time is reached
     * @param weekdays weekdays on which this reminder is active
     * @return reminder with the specific parameters.
     */
    public static Reminder createReminder(int hours, int minutes, @NonNull Runnable callback, DayOfWeek... weekdays) {
        return server.createReminder(hours, minutes, callback, weekdays);
    }

    /**
     * Creates a reminder with the specific parameters.
     *
     * @param hours    hours on which this reminder is called
     * @param minutes  minutes on which this reminder is called
     * @param callback callback that is called when the given date/time is reached
     * @param weekdays weekdays on which this reminder is active
     * @return reminder with the specific parameters.
     */
    public static Reminder createReminder(int hours, int minutes, @NonNull Runnable callback, List<DayOfWeek> weekdays) {
        return server.createReminder(hours, minutes, callback, weekdays);
    }

    /**
     * Creates a new Discord Webhook.
     *
     * @param url URL of Webhook
     * @return Webhook with given URL
     */
    public static Webhook createWebhook(String url) {
        return server.createWebhook(url);
    }

    /**
     * Creates an embedded message to be sent via webhook
     *
     * @return embedded message
     */
    public static Webhook.EmbeddedMessage createEmbeddedMessage() {
        return server.createEmbeddedMessage();
    }

    /**
     * Gets whether the server is currently in maintenance.
     *
     * @return true, if the server is in maintenance
     */
    public static boolean isMaintenance() {
        return server.isMaintenance();
    }

    @Getter
    @AllArgsConstructor
    public static class PosItem {
        private final int position;
        private final WindowItem guiItem;
    }
}