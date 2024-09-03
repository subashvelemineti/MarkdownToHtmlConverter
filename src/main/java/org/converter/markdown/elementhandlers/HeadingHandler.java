package org.converter.markdown.elementhandlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeadingHandler implements MarkdownElementHandler {

    private static final Pattern HEADING_PATTERN = Pattern.compile("^(#+) (.+)$");

    @Override
    public boolean canHandle(final String line) {
        final Matcher matcher = HEADING_PATTERN.matcher(line);
        if (matcher.matches()) {
            final int level = matcher.group(1).length();
            return level >= 1 && level <= 6;
        }
        return false;
    }

    @Override
    public String handle(final String line, final StringBuilder html) {
        final int level = line.lastIndexOf('#') + 1;
        final String content = LinkHandler.convert(line.substring(level).trim());
        return "<h" + level + ">" + content + "</h" + level + ">\n";
    }
}
