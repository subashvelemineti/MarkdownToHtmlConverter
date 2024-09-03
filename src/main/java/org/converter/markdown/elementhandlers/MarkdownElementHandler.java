package org.converter.markdown.elementhandlers;

public interface MarkdownElementHandler {
    boolean canHandle(final String line);

    String handle(final String line, final StringBuilder html);

    default void close(final StringBuilder html) {

    }
}
