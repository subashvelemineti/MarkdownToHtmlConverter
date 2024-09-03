package org.converter.markdown.elementhandlers;

public class LinkHandler {
    public static String convert(final String line) {
        return line.replaceAll("\\[(.*?)\\]\\((.*?)\\)", "<a href=\"$2\">$1</a>");
    }
}
