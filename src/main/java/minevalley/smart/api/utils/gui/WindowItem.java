package minevalley.smart.api.utils.gui;

import org.bukkit.inventory.ItemStack;

public interface WindowItem {

    /**
     * Defines the itemstack of this gui-item
     * @param itemStack new itemstack
     */
    void setItemStack(ItemStack itemStack);

    ItemStack getItemStack();
}