package com.github.schlak.database;

/**
 * Created by Jonas Schlak on 26.12.2016.
 */
public class Debug {

    /**
     * The method is used to create an output that could be controlled by a flag.
     * It allows to generate a Output with a custom tag that is printed before the message
     * to easily identify the output.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void out(String tag, String message) {
        if (System.getenv("SYSOUT") != null && System.getenv("SYSOUT").equals("true")) {
            System.out.println(tag + ": " + message);
        }
    }

    /**
     * The method is used to create an output that could be controlled by a flag.
     * It uses the default tag "DEBUG OUTPUT".
     *
     * @param message the message
     */
    public static void out(String message) {
        out("DEBUG OUTPUT", message);
    }
}
