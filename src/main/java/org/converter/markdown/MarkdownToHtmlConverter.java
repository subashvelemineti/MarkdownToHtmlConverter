package org.converter.markdown;

import org.converter.markdown.processor.MarkdownProcessor;

public class MarkdownToHtmlConverter {
    public static void main(String[] args) {
        final MarkdownProcessor processor = new MarkdownProcessor();
        final String markdownSample1 = """
                                       # Sample Document
                                                      
                                       Hello!
                                                      
                                       This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.                               
                                       """;
        System.out.println("\n Markdown to HTML conversion \n Sample 1: \n");
        System.out.println(processor.convertToHtml(markdownSample1));

        System.out.println("\n Markdown to HTML conversion \n Sample 2: \n");
        final String markdownSample2 = """
                                       # Header one
                                            
                                       Hello there
                                            
                                       How are you?
                                       What's going on?
                                            
                                       ## Another Header
                                            
                                       This is a paragraph [with an inline link](http://google.com). Neat, eh?
                                            
                                       ## This is a header [with a link](http://yahoo.com)
                                       """;
        System.out.println(processor.convertToHtml(markdownSample2));
    }
}
