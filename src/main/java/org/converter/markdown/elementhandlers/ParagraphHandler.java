package org.converter.markdown.elementhandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphHandler implements MarkdownElementHandler {

    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile(".*\\[.*\\]\\(.*\\).*");
    private final List<String> paragraphBuffer = new ArrayList<>();

    @Override
    public boolean canHandle(final String line) {
        return true; // Paragraph handler can handle any line that isn't a heading
    }

    @Override
    public String handle(final String line, final StringBuilder html) {
        final Matcher matcher = PARAGRAPH_PATTERN.matcher(line);
        if (matcher.matches()) {
            paragraphBuffer.add(LinkHandler.convert(line));
        } else {
            paragraphBuffer.add(line);
        }
        return "";
    }

    @Override
    public void close(final StringBuilder html) {
        if (!paragraphBuffer.isEmpty()) {
            html.append("<p>");
            for (String paragraphLine : paragraphBuffer) {
                html.append(paragraphLine).append(" ");
            }
            html.setLength(html.length() - 1); // Remove trailing space
            html.append("</p>\n");
            paragraphBuffer.clear();
        }
    }
}
