package org.eclipse.paho.client.mqttv3.logging;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: classes.dex */
public class LoggerFactory {
    public static final String MQTT_CLIENT_MSG_CAT = "org.eclipse.paho.client.mqttv3.internal.nls.logcat";
    private static final String CLASS_NAME = LoggerFactory.class.getName();
    private static String overrideloggerClassName = null;
    private static String jsr47LoggerClassName = JSR47Logger.class.getName();

    public static Logger getLogger(String str, String str2) {
        String str3 = overrideloggerClassName;
        if (str3 == null) {
            str3 = jsr47LoggerClassName;
        }
        Logger logger = getLogger(str3, ResourceBundle.getBundle(str), str2, null);
        if (logger == null) {
            throw new MissingResourceException("Error locating the logging class", CLASS_NAME, str2);
        }
        return logger;
    }

    private static Logger getLogger(String str, ResourceBundle resourceBundle, String str2, String str3) {
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null) {
                return null;
            }
            try {
                Logger logger = (Logger) cls.newInstance();
                logger.initialise(resourceBundle, str2, str3);
                return logger;
            } catch (ExceptionInInitializerError e) {
                return null;
            } catch (IllegalAccessException e2) {
                return null;
            } catch (InstantiationException e3) {
                return null;
            } catch (SecurityException e4) {
                return null;
            }
        } catch (ClassNotFoundException e5) {
            return null;
        } catch (NoClassDefFoundError e6) {
            return null;
        }
    }

    public static String getLoggingProperty(String str) {
        try {
            Class<?> cls = Class.forName("java.util.logging.LogManager");
            return (String) cls.getMethod("getProperty", String.class).invoke(cls.getMethod("getLogManager", new Class[0]).invoke(null, null), str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void setLogger(String str) {
        overrideloggerClassName = str;
    }
}
