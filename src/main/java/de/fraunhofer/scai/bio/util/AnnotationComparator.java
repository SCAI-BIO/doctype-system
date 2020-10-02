package de.fraunhofer.scai.bio.util;

import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;

import java.util.Comparator;

/**
 * Compares two Annotations based on Offsets.
 * Returns -1 if anno1 starts/ends before annot2
 * Returns 1 if anno1 start/ends after anno2
 * Returns 0 of located at the same location
 */
public class AnnotationComparator implements Comparator<Annotation> {

    @Override
    public int compare(Annotation anno1, Annotation anno2) {

        if (anno1 == null) {
            return -1;
        }
        if (anno2 == null) {
            return 1;
        }

        if (anno1.getStartOffset() < anno2.getStartOffset()) {
            return -1;
        }
        if (anno1.getStartOffset() > anno2.getStartOffset()) {
            return 1;
        }

        if (anno1.getEndOffset() < anno2.getEndOffset()) {
            return -1;
        }
        if (anno1.getEndOffset() > anno2.getEndOffset()) {
            return 1;
        }

        return 0;
    }

}
