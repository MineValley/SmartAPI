package minevalley.smart.api;

import com.google.gson.Gson;
import minevalley.smart.api.enums.WindowSize;
import minevalley.smart.api.utils.ItemBuilder;
import minevalley.smart.api.utils.window.WindowBuilder;
import minevalley.smart.api.utils.window.WindowItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface SmartServer {

    int runSyncTaskLater(String taskName, long delay, Runnable runnable);

    int runAsyncTaskLater(String taskName, long delay, Runnable runnable);

    int runSyncTaskPeriodically(String taskName, long delay, long period, Runnable runnable);

    int runAsyncTaskPeriodically(String taskName, long delay, long period, Runnable runnable);

    boolean isCurrentlyRunning(int taskId);

    void cancelTask(int taskId);

    void setSetting(String key, String value);

    String getSetting(String key);

    String getUserSetting(String uniqueId, String key);

    void updateStatistic(String key, double value);

    double getStatistic(String key);

    String getName(String uniqueId);

    String getUniqueId(String name);

    int randomInteger(int chars);

    boolean isInteger(String string);

    boolean isDouble(String string);

    String formatMoney(int amountInCents);

    WindowBuilder window(WindowSize size, boolean endSessionOnMinimize);

    WindowBuilder window(List<WindowItem> items, String title, Smart.PosItem... posItems);

    WindowItem windowItem(ItemStack itemStack, Consumer<Session> consumer);

    WindowItem advancedWindowItem(ItemStack itemStack, BiConsumer<Session, ClickType> consumer);

    ItemBuilder createItem(ItemStack itemStack);

    ItemBuilder createItem(Material material);

    ItemBuilder createItem(Material material, int data);

    ItemBuilder createItem(Player player);

    ItemBuilder createItem(UUID uniqueId);

    ItemBuilder createItem(String url);

    boolean isMaintenance();

    List<Session> getAllSessions(SmartApp app);

    SmartApp getSmartApp(String systemName);

    Gson getGson();
}