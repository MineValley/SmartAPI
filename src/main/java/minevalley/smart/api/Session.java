package minevalley.smart.api;

import minevalley.smart.api.utils.window.Window;
import org.bukkit.inventory.ItemStack;

public interface Session {

    User getUser();

    void openWindow(Window window);

    Window getCurrentWindow();

    void purchase(double price, ItemStack infoItem, Runnable onSuccess, Runnable onCancel);

    void close();
}
