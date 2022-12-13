package minevalley.smart.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

    /**
     * Name of this app.
     * <p>
     * <b>Note:</b> This will be visible whenever this app is mentioned!
     * </p>
     */
    String name();

    int id();

    /**
     * Version of development.
     * <p>
     * <b>Note:</b> This will be visible in the apps description!
     * </p>
     */
    String version() default "1.0";

    /**
     * List of all developers who contributed to this app.
     * <p>
     * <b>Note:</b> This will be visible in the apps description!
     * </p>
     */
    String[] authors();

    /**
     * id of the head which will be used as an app icon in app store and phone menu.
     * <p>
     * <b>Attention:</b> In order to maintain the recognizability of the apps, make sure that the head is not yet used in another app!
     * </p>
     */
    String iconHead();

    /**
     * Price that has to be paid in order to be able to download the app from the app store.
     */
    int price() default 0;

    /**
     * If true, this app will be preinstalled on everyones phone.
     * <p>
     * <b>Note:</b> This is only available for apps that are a must-have feature. It should not be used to increase the popularity of apps that are not essential.
     * </p>
     */
    boolean preInstalled() default false;

    /**
     * This defines the position of this app in the phone menu.
     * <p>
     * <b>Note:</b> Do not change this options from its default value when preInstalled() is set to false!
     * </p>
     */
    int priority() default 0;
}
