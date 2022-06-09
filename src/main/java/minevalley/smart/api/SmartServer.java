package minevalley.smart.api;

import minevalley.smart.api.utils.ItemBuilder;
import minevalley.smart.api.utils.window.WindowBuilder;
import minevalley.smart.api.utils.window.WindowItem;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

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

    void registerListeners(Listener listener);

    void sendTeamChatMessage(String message);

    void sendTeamChatMessage(BaseComponent[] message);

    void setSetting(String key, String value);

    String getSetting(String key);

    void updateStatistic(String key, double value);

    double getStatistic(String key);

    User getUser(Player player);

    void setMetadata(Metadatable metadatable, String key, Object value);

    void removeMetadata(Metadatable metadatable, String key);

    List<MetadataValue> getMetadata(Metadatable metadatable, String key);

    String getName(String uniqueId);

    String getUniqueId(String name);

    int randomInteger(int chars);

    boolean isNumeric(String string);

    String formatMoney(double amount);

    WindowBuilder window(Inventory inventory);

    WindowBuilder window(int size);

    WindowBuilder window(List<WindowItem> items, int size, String title, Smart.PosItem... posItems);

    WindowItem windowItem(ItemStack itemStack, Consumer<User> consumer);

    WindowItem advancedWindowItem(ItemStack itemStack, BiConsumer<User, ClickType> consumer);

    ItemBuilder createItem(ItemStack itemStack);

    ItemBuilder createItem(Material material);

    ItemBuilder createItem(Material material, int data);

    ItemBuilder createItem(Player player);

    ItemBuilder createItem(UUID uniqueId);

    ItemBuilder createItem(String url);

    boolean isMaintenance();
}