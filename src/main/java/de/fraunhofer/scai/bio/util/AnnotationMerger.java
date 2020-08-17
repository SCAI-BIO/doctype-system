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

    public Document mergeAnnotations(Document baseDocument, Document extensionDocument) {
        Map<UUID, TextElement> textElementMapDocument1 = DocumentRenderer.getDocumentTextElements(baseDocument)
            .values()
            .stream()
            .collect(Collectors.toMap(p -> p.getUuid(), p -> p));

        Map<UUID, TextElement> textElementMapDocument2 = DocumentRenderer.getDocumentTextElements(extensionDocument)
            .values()
            .stream()
            .collect(Collectors.toMap(p -> p.getUuid(), p -> p));

        textElementMapDocument1.values().parallelStream().forEach(s -> {
            TextElement other = textElementMapDocument2.get(s.getUuid());
            if (other != null) {
                if (s.getAnnotations() == null) {
                    s.setAnnotations(other.getAnnotations());
                } else {
                    s.getAnnotations().addAll(other.getAnnotations());
                }
            }
        });
        return baseDocument;

    }
}
