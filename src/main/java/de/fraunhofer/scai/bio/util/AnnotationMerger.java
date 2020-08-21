package de.fraunhofer.scai.bio.util;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import de.fraunhofer.scai.bio.Document;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * Takes two Documents and merge
 */
public class AnnotationMerger {

    public static Document mergeAnnotations(Document baseDocument, Document extensionDocument) {
        Map<UUID, TextElement> textElementMapDocument1 = DocumentRenderer.getDocumentTextElements(baseDocument)
            .values()
            .stream()
            .collect(Collectors.toMap(p -> p.getUuid(), p -> p,(a,b)->a));

        Map<UUID, TextElement> textElementMapDocument2 = DocumentRenderer.getDocumentTextElements(extensionDocument)
            .values()
            .stream()
            .collect(Collectors.toMap(p -> p.getUuid(), p -> p,(a,b)->a));

        textElementMapDocument1.values().stream().forEach(s -> {
            TextElement other = textElementMapDocument2.get(s.getUuid());
            if (other != null) {
                if (s.getAnnotations() == null) {
                    if (other.getAnnotations() != null) {
                        s.setAnnotations(other.getAnnotations());
                    }
                } else {
                    if (other.getAnnotations() != null) {
                        s.getAnnotations().addAll(other.getAnnotations());
                    }
                }
            }
        });
        return baseDocument;

    }
}
