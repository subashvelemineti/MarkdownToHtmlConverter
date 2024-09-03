package org.converter.markdown.processor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkdownProcessorTest {

    @Test
    public void testHeading() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          # This is a heading one
                          """;
        String expectedHtml = """
                              <h1>This is a heading one</h1>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testMultipleHeadings() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          # Heading 1
                          ## Heading 2
                          ### Heading 3
                          #### Heading 4
                          ##### Heading 5
                          ###### Heading 6
                          """;
        String expectedHtml = """
                              <h1>Heading 1</h1>
                              <h2>Heading 2</h2>
                              <h3>Heading 3</h3>
                              <h4>Heading 4</h4>
                              <h5>Heading 5</h5>
                              <h6>Heading 6</h6>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testHeadingWithLink() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = "# Heading 1 with [link](http://google.com)";
        String expectedHtml = "<h1>Heading 1 with <a href=\"http://google.com\">link</a></h1>";
        assertEquals(expectedHtml, processor.convertToHtml(markdown));
    }

    @Test
    public void testMultipleHeadingsWithLinks() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          ## This is a heading two [with a link](http://yahoo.com)
                          ### This is a heading three [with a link](http://yahoo.com)
                          """;
        String expectedHtml = """
                              <h2>This is a heading two <a href=\"http://yahoo.com\">with a link</a></h2>
                              <h3>This is a heading three <a href=\"http://yahoo.com\">with a link</a></h3>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testParagraph() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          This is a paragraph.
                          """;
        String expectedHtml = """
                              <p>This is a paragraph.</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testMultipleParagraphs() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          First paragraph.
                          
                          Second paragraph.
                          """;
        String expectedHtml = """
                              <p>First paragraph.</p>
                              <p>Second paragraph.</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testLink() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          [link](http://google.com).
                          """;
        String expectedHtml = """
                              <p><a href=\"http://google.com\">link</a>.</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testSingleParagraphWithLink() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = "This is a paragraph with a [link](http://google.com).";
        String expectedHtml = "<p>This is a paragraph with a <a href=\"http://google.com\">link</a>.</p>";
        assertEquals(expectedHtml, processor.convertToHtml(markdown));
    }

    @Test
    public void testMultipleParagraphsWithLinks() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          First paragraph with a [link](http://google.com).
                          
                          Second paragraph with a [link](http://google.com).
                          """;
        String expectedHtml = """
                              <p>First paragraph with a <a href=\"http://google.com\">link</a>.</p>
                              <p>Second paragraph with a <a href=\"http://google.com\">link</a>.</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testMultipleLinesInSingleParagraph() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          Hello there
                          How are you?
                          What's going on?
                          """;
        String expectedHtml = """
                              <p>Hello there How are you? What's going on?</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testHeadingFollowedByParagraph() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          # Heading
                          
                          This is a paragraph.
                          """;
        String expectedHtml = """
                              <h1>Heading</h1>
                              <p>This is a paragraph.</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testParagraphFollowedByHeading() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          This is a paragraph.
                          # Heading
                          """;
        String expectedHtml = """                             
                              <p>This is a paragraph.</p>
                              <h1>Heading</h1>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testHeadingFollowedByParagraphAndParagraphWithLink() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          # Sample Document
                          
                          Hello!
                          
                          This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.                                         
                          """;
        String expectedHtml = """
                              <h1>Sample Document</h1>
                              <p>Hello!</p>
                              <p>This is sample markdown for the <a href="https://www.mailchimp.com">Mailchimp</a> homework assignment.</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testMultipleHeadingsAndMultipleParagraphsWithLinks() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          # Header one

                          Hello there

                          How are you?
                          What's going on?

                          ## Another Header

                          This is a paragraph [with a link](http://google.com). Neat, eh?

                          ## This is a header [with a link](http://yahoo.com)
                                        
                          """;
        String expectedHtml = """
                              <h1>Header one</h1>
                              <p>Hello there</p>
                              <p>How are you? What's going on?</p>
                              <h2>Another Header</h2>
                              <p>This is a paragraph <a href=\"http://google.com\">with a link</a>. Neat, eh?</p>
                              <h2>This is a header <a href=\"http://yahoo.com\">with a link</a></h2>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testNonStandardHeading() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = "######### Too many hashes";
        String expectedHtml = "<p>######### Too many hashes</p>";
        assertEquals(expectedHtml, processor.convertToHtml(markdown));
    }

    @Test
    public void testStandardHeadingsWithNonStandardHeading() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
        # Header one
        ###### Header six
        ######### Too many hashes
        """;
        String expectedHtml = """
        <h1>Header one</h1>
        <h6>Header six</h6>
        <p>######### Too many hashes</p>
        """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testHeadingsWithOutSpaces() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
        #Header one
        
        ######Header six
        
        #########Too many hashes
        """;
        String expectedHtml = """
        <p>#Header one</p>
        <p>######Header six</p>
        <p>#########Too many hashes</p>
        """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testHeadingsInsideParagraphs() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          This is a paragraph with a ## Heading.
                          """;
        String expectedHtml = """
                              <p>This is a paragraph with a ## Heading.</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testStandardAndNonstandardHeadingsAcrossParagraphs() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = """
                          This is a paragraph
                          [with a link](http://google.com)
                          and continuation
                          of the paragraph encountering
                          ###### a valid heading
                          followed by a
                          paragraph
                          
                          ########## invalid heading [with a link](http://yahoo.com)                     
                          wrapped in a paragraph
                          """;
        String expectedHtml = """
                              <p>This is a paragraph <a href=\"http://google.com\">with a link</a> and continuation of the paragraph encountering</p>
                              <h6>a valid heading</h6>
                              <p>followed by a paragraph</p>
                              <p>########## invalid heading <a href="http://yahoo.com">with a link</a> wrapped in a paragraph</p>
                              """;
        assertEquals(expectedHtml.trim(), processor.convertToHtml(markdown));
    }

    @Test
    public void testEmptyInput() {
        MarkdownProcessor processor = new MarkdownProcessor();
        String markdown = "";
        String expectedHtml = "";
        assertEquals(expectedHtml, processor.convertToHtml(markdown));
    }
}
