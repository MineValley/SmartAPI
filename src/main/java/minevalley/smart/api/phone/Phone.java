package minevalley.smart.api.phone;

public interface Phone extends Telephone {

    /**
     * This method isn't useable yet.
     */
    void sendNotification();

    /**
     * Activates the player's phone.
     */
    void activate();

    /**
     * Deactivates the player's phone.
     */
    void deactivate();

    /**
     * Gets whether the phone is activated.
     *
     * @return true, if phone is activated
     */
    boolean isActivated();

    /**
     * Gets whether the phone is connected to the network.
     *
     * @return true, if phone is connected to the network
     */
    boolean isConnected();
}