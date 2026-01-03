package minevalley.smart.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SuppressWarnings("unused")
public @interface App {

    /**
     * Name of this app.
     * <p>
     * <b>Note:</b> This will be visible whenever this app is mentioned!
     * </p>
     */
    String name();

    String systemName();

    /**
     * The id of the head which will be used as an app icon in app store and phone menu.
     * <br>
     * <b>Note:</b> In order to maintain the distinctness of the apps, make sure that the head is not yet used in another app!
     */
    String iconHead();

    /**
     * Price that has to be paid in order to be able to download the app from the app store.
     */
    int price() default 0;

    /**
     * If true, this app will be preinstalled on everyone's phone.
     * <p>
     * <b>Note:</b> This is only available for apps that are a must-have feature. It should not be used to increase the popularity of apps that are not essential.
     * </p>
     */
    boolean preinstalled() default false;

    /**
     * This defines the position of this app in the phone menu.
     * <br>
     * <b>Note:</b> Do not change this options from its default value when {@link #preinstalled()} is set to false!
     */
    int priority() default 0;
}
