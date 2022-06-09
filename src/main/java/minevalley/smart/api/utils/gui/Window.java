package minevalley.smart.api.utils.gui;

public interface Window {

    Window update();

    Window updateItem(WindowItem guiItem);

    Window updateItem(WindowItem guiItem, WindowItem updateItem);

    void clear();

    void fix();

    void addItem(int slot, WindowItem guiItem);

    void addItem(WindowItem guiItem);

    void removeItem(WindowItem guiItem);

    WindowItem getItem(int slot);
}