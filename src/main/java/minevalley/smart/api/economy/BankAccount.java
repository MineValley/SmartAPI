package minevalley.smart.api.economy;

import minevalley.smart.api.Registered;
import minevalley.smart.api.User;

import java.util.List;

public interface BankAccount {

    /**
     * Gets the holder of this bank account.
     *
     * @return holder of this bank account
     */
    Registered getHolder();

    /**
     * Gets this accounts banking id.
     * <b>Note:</b> the IBAN already contains the prefix (DE00/01/02/03)
     *
     * @return this accounts banking id
     */
    String getIban();

    /**
     * Gets the amount of money in this bank account.
     *
     * @return amount of money in this bank account
     */
    double getAmount();

    /**
     * Gets a list of the registered that are permissioned to use this bank account.
     *
     * @return list of registered
     */
    List<Registered> getPermissioned();

    /**
     * Removes a specific permissioned registered.
     *
     * @param registered registered to remove
     */
    void removeFromPermissioned(Registered registered);

    /**
     * Gets whether the specific user is permissioned to use this bank account.
     *
     * @param user user who could be permissioned
     * @return true, if the specific user is permissioned to use this bank account
     */
    boolean isPermissioned(User user);

    /**
     * Checks if this bank account is government owned.
     * Government bank accounts have no amount and can send or receive unlimited funds.
     *
     * @return true, if this account is government owned
     */
    boolean isState();

    /**
     * Transfers an amount to another bank account.
     * <p>
     * <b>Note:</b> This method only accepts positive amounts!
     *
     * @param target bank account to which the money is transfered to
     * @param amount amount of money to be transfered
     * @param usage  usage as string
     * @return true, if the transfer was successful (false, if account does not have enough money)
     */
    boolean transfer(BankAccount target, double amount, String usage);
}