package org.converter.markdown.processor;

import org.converter.markdown.elementhandlers.HeadingHandler;
import org.converter.markdown.elementhandlers.MarkdownElementHandler;
import org.converter.markdown.elementhandlers.ParagraphHandler;

import java.util.ArrayList;
import java.util.List;

public class MarkdownProcessor {
    private final List<MarkdownElementHandler> handlers = new ArrayList<>();
    private MarkdownElementHandler currentHandler;

    public MarkdownProcessor() {
        handlers.add(new HeadingHandler());
        handlers.add(new ParagraphHandler());
    }

    public String convertToHtml(final String markdown) {
        StringBuilder html = new StringBuilder();
        final String[] lines = markdown.split("\n");

        for (String line : lines) {
            processLine(line.trim(), html);
        }

        closeCurrentHandler(html);

        return html.toString().trim();
    }

    private void processLine(final String line, StringBuilder html) {
        if (line.isEmpty()) {
            closeCurrentHandler(html);
            return;
        }

        final MarkdownElementHandler handler = findHandlerForLine(line);
        if (handler != null) {
            switchHandlerIfNecessary(handler, html);
            html.append(handler.handle(line, html));
            currentHandler = handler;
        }
    }

    private MarkdownElementHandler findHandlerForLine(final String line) {
        for (MarkdownElementHandler handler : handlers) {
            if (handler.canHandle(line)) {
                return handler;
            }
        }
        return null;
    }

    private void switchHandlerIfNecessary(final MarkdownElementHandler newHandler, StringBuilder html) {
        if (currentHandler != null && currentHandler != newHandler) {
            currentHandler.close(html);
        }
    }

    private void closeCurrentHandler(StringBuilder html) {
        if (currentHandler != null) {
            currentHandler.close(html);
            currentHandler = null;
        }
    }
}

