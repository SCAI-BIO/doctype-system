package de.fraunhofer.scai.bio.util;


import static junit.framework.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import de.fraunhofer.scai.bio.Document;
import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;

public class AnnotationMergerTest {


    @Test
    public void testMergeAnnotations() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultMergeable(true);
        File json0 = new File(getClass().getResource("/a.json").getPath());
        File json1 = new File(getClass().getResource("/b.json").getPath());
        Document oldDocument = mapper.readValue(json0.getAbsoluteFile(), Document.class);
        Document newDocument = mapper.readValue(json1.getAbsoluteFile(), Document.class);
        Set<Annotation> oldD = oldDocument.getDocumentElement().getFrontMatter().getTitleText().getAnnotations();
        Set<Annotation> newD = newDocument.getDocumentElement().getFrontMatter().getTitleText().getAnnotations();


        AnnotationMerger annotationMerger = new AnnotationMerger();
        Document a = annotationMerger.mergeAnnotations(oldDocument, newDocument);
        Set<Annotation> merged = a.getDocumentElement().getFrontMatter().getTitleText().getAnnotations();
        assertEquals(22, merged.size());


    }
}
