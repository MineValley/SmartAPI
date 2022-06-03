package minevalley.smart.api.database;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface DatabaseEntry {

    /**
     * Removes the specific database entry from the table.
     */
    void remove();

    /**
     * Checks if the database-entry exists.
     *
     * @return true if the selected database entry exists in the table
     */
    boolean exists();

    /**
     * Changes a value of the selected entry.
     *
     * @param value value object with the column and the new value
     */
    void changeValue(Value value);

    /**
     * Gets the string at the given column.
     *
     * @param column name of the column
     * @return string at given column from the selected database entry
     */
    String getString(String column);

    /**
     * Gets the integer at the given column.
     *
     * @param column name of the column
     * @return integer at given column from the selected database entry
     */
    Integer getInteger(String column);

    /**
     * Gets the boolean at the given column.
     *
     * @param column name of the column
     * @return boolean at given column from the selected database entry
     */
    Boolean getBoolean(String column);

    /**
     * Gets the double at the given column.
     *
     * @param column name of the column
     * @return double at given column from the selected database entry
     */
    Double getDouble(String column);

    /**
     * Gets the location at the given column (with pitch & yaw).
     *
     * @param column name of the column
     * @return location at given column from the selected database entry
     */
    Location getLocation(String column);

    /**
     * Gets the itemstack at the given column.
     *
     * @param column name of the column
     * @return itemstack at given column from the selected database entry
     */
    ItemStack getItemStack(String column);

    /**
     * Gets the itemstack[] at the given column.
     *
     * @param column name of the column
     * @return itemstack[] at given column from the selected database entry
     */
    ItemStack[] getItemStacks(String column);
}