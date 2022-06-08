package minevalley.smart.api;

import com.google.gson.Gson;
import lombok.NonNull;
import minevalley.smart.api.corporations.Department;
import minevalley.smart.api.corporations.Group;
import minevalley.smart.api.database.DatabaseEntry;
import minevalley.smart.api.database.DatabaseEntryCollection;
import minevalley.smart.api.database.DatabaseTable;
import minevalley.smart.api.database.Value;
import minevalley.smart.api.economy.BankAccount;
import minevalley.smart.api.modulepipeline.Container;
import minevalley.smart.api.timing.Reminder;
import minevalley.smart.api.timing.RepeatingTimer;
import minevalley.smart.api.timing.Timer;
import minevalley.smart.api.utils.ClickableMessage;
import minevalley.smart.api.utils.Countdown;
import minevalley.smart.api.utils.EventListener;
import minevalley.smart.api.utils.ItemBuilder;
import minevalley.smart.api.utils.gui.WindowBuilder;
import minevalley.smart.api.utils.gui.WindowItem;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Material;
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
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface SmartServer {

    int scheduleSyncRepeatingTask(long delay, long period, BukkitRunnable runnable);

    int scheduleSyncRepeatingTask(long delay, long period, Runnable runnable);

    int scheduleAsyncRepeatingTask(long delay, long period, Runnable runnable);

    int scheduleSyncDelayedTask(long delay, BukkitRunnable runnable);

    int scheduleSyncDelayedTask(long delay, Runnable runnable);

    int scheduleSyncDelayedTask(BukkitRunnable runnable);

    int scheduleSyncDelayedTask(Runnable runnable);

    int scheduleAsyncDelayedTask(long delay, Runnable runnable);

    int scheduleAsyncDelayedTask(Runnable runnable);


    BukkitTask runTask(Runnable runnable);

    BukkitTask runTaskAsync(Runnable runnable);


    BukkitTask runTaskTimer(long delay, long period, BukkitRunnable runnable);

    BukkitTask runTaskTimer(long delay, long period, Runnable runnable);

    BukkitTask runTaskTimerAsync(long delay, long period, BukkitRunnable runnable);

    BukkitTask runTaskTimerAsync(long delay, long period, Runnable runnable);


    BukkitTask runTaskLater(long delay, BukkitRunnable runnable);

    BukkitTask runTaskLater(long delay, Runnable runnable);

    BukkitTask runTaskLaterAsync(long delay, BukkitRunnable runnable);

    BukkitTask runTaskLaterAsync(long delay, Runnable runnable);


    boolean isCurrentlyRunning(int taskId);

    void cancelTask(int taskId);

    JavaPlugin getInstance();

    void registerEvent(Class<? extends Event> cls, EventListener listener);

    void unregisterEvent(Class<? extends Event> cls, EventListener listener);

    void registerListeners(Listener listener);

    void registerPipelineReceiver(PipelineReceiver pipeLineManager);

    void sendPipelineContainer(String pipelineName, Container container);


    void sendTeamChatMessage(String message);

    void sendTeamChatMessage(BaseComponent[] message);

    DatabaseEntry databaseEntry(String tableName, ResultSet resultSet, int index);

    DatabaseEntry databaseEntry(String tableName, Value searchValue);

    DatabaseEntryCollection databaseEntryCollection(String tableName, List<DatabaseEntry> entries);

    DatabaseEntryCollection databaseEntryCollection(String tableName, Value searchValue);

    DatabaseTable databaseTable(String tableName);

    void setSetting(String key, String value);

    String getSetting(String key);

    void updateStatistic(String key, double value);

    double getStatistic(String key);

    User getUser(Player player);

    ClickEvent createClickEvent(boolean selfCancelling, Consumer<User> callback);

    ClickableMessage createClickableMessage();

    ClickableMessage createClickableMessage(Consumer<User> callback);

    ClickableMessage createClickableMessage(Consumer<User> callback, boolean selfCancelling);

    ChatMenu createChatMenu(ChatMenu.Option... options);

    ChatMenu createChatMenu();

    void setMetadata(Metadatable metadatable, String key, Object value);

    void removeMetadata(Metadatable metadatable, String key);

    List<MetadataValue> getMetadata(Metadatable metadatable, String key);

    Gson getGson();

    String getName(String uniqueId);

    String getUniqueId(String name);

    String removeColorCodes(String text);

    String convertColorCodes(String text);

    int randomInteger(int chars);

    boolean isNumeric(String string);

    String formatMoney(double amount);

    WindowBuilder gui(Inventory inventory);

    WindowBuilder gui(int size);

    WindowBuilder gui(List<WindowItem> items, int size, String title, Smart.PosItem... posItems);

    WindowItem guiItem(ItemStack itemStack, Consumer<User> consumer);

    WindowItem advancedGuiItem(ItemStack itemStack, BiConsumer<User, ClickType> consumer);

    Countdown createCountdown();

    void startCountdown(Countdown countdown);

    void stopCountdown(Countdown countdown);

    ItemBuilder createItem(ItemStack itemStack);

    ItemBuilder createItem(Material material);

    ItemBuilder createItem(Material material, int data);

    ItemBuilder createItem(Player player);

    ItemBuilder createItem(UUID uniqueId);

    ItemBuilder createItem(String url);

    Inventory getInventoryFromString(String inventory);

    String getStringFromInventory(Inventory inventory);

    BankAccount getBankAccount(String iban);

    BankAccount createBankAccount(Registered holder);

    Group getGroup(int id);

    Group getGroup(String name);

    boolean containsForForbiddenWords(String string);

    Registered getRegistered(User user);

    Registered getRegistered(Group group);

    Registered getRegistered(Department department);

    Registered getRegistered(String string);

    Timer startTimer(int delay, @NonNull Runnable callback);

    RepeatingTimer startRepeatingTimer(int delay, int period, @NonNull Runnable callback);

    Reminder createReminder(int hours, int minutes, @NonNull Runnable callback, DayOfWeek... weekdays);

    Reminder createReminder(int hours, int minutes, @NonNull Runnable callback, List<DayOfWeek> weekdays);

    Webhook createWebhook(String url);

    Webhook.EmbeddedMessage createEmbeddedMessage();

    boolean isMaintenance();
}