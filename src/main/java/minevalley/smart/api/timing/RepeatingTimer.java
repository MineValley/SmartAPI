package minevalley.smart.api.timing;

/**
 * Repeating timers are used to call continuous actions that are supposed to be called in a period longer than 2 minutes (for shorter spans use schedulers).
 * <p>
 * <b>Note:</b> When adding a delay to a repeating timer, only the first period is delayed.
 */
public interface RepeatingTimer extends Timer {

    /**
     * Gets this timers period.
     *
     * @return period as int
     */
    int getPeriod();
}
