package minevalley.smart.api.utils.gui;

import minevalley.smart.api.Session;
import org.bukkit.inventory.Inventory;

public interface Window {

    Window open(Session session);

    Window update();

    Window updateItem(WindowItem guiItem);

    Window updateItem(WindowItem guiItem, WindowItem updateItem);

    void clear();

    void fix();

    void addItem(int slot, WindowItem guiItem);

    void addItem(WindowItem guiItem);

    void removeItem(WindowItem guiItem);

    Inventory getInventory();

    WindowItem getItem(int slot);
}