package com.opuslex.component;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentChunkerTest {
    private final DocumentChunker chunker = new DocumentChunker();

    @Test
    public void testShortDocument() {
        String text = "This is a short document.";
        List<String> chunks = chunker.chunkText(text, 1000, 200);
        assertEquals(1, chunks.size());
        assertEquals(text, chunks.get(0));
    }

    @Test
    public void testExactlySize() {
        String text = "a".repeat(1000);
        List<String> chunks = chunker.chunkText(text, 1000, 200);
        assertEquals(2, chunks.size());
        assertEquals(1000, chunks.get(0).length());
        assertEquals(text, chunks.get(0));
        assertEquals(200, chunks.get(1).length());
        assertEquals(text.substring(800), chunks.get(1));
    }

    @Test
    public void testLongDocumentWithOverlap() {
        String text = "a".repeat(1000) + "b".repeat(500);
        List<String> chunks = chunker.chunkText(text, 1000, 200);
        assertEquals(2, chunks.size());
        assertEquals(1000, chunks.get(0).length());
        assertEquals("a".repeat(1000), chunks.get(0));
        // Overlap of 200 means second chunk starts at index 800
        // Total length is 1500. So second chunk is from 800 to 1500 (length 700)
        assertEquals(700, chunks.get(1).length());
        assertEquals("a".repeat(200) + "b".repeat(500), chunks.get(1));
    }

    @Test
    public void testEmptyInput() {
        assertEquals(0, chunker.chunkText("", 1000, 200).size());
        assertEquals(0, chunker.chunkText(null, 1000, 200).size());
    }
}
