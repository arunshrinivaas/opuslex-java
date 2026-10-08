package com.opuslex.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class PdfExtractor {

    public String extractTextFromPdf(InputStream pdfStream) {
        try (PDDocument document = org.apache.pdfbox.Loader.loadPDF(pdfStream.readAllBytes())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract text from PDF", e);
        }
    }
}
