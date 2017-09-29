package no.cantara.base.util;

/**
 * Utillity methods
 * Created by baardl on 2017-03-29.
 */
public class StringHelper {

    public static boolean hasContent(String text) {
        boolean hasContent = false;
        if (text != null && !text.isEmpty()) {
            hasContent = true;
        }
        return hasContent;
    }
}
