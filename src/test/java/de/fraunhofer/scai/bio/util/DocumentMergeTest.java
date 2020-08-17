package de.fraunhofer.scai.bio.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import org.junit.Test;

import de.fraunhofer.scai.bio.Document;
import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;

public class DocumentMergeTest {

    @Test
    public void test() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultMergeable(true);
        File json0 = new File(getClass().getResource("/a.json").getPath());
        File json1 = new File(getClass().getResource("/b.json").getPath());

        Document oldDocument = mapper.readValue(json0.getAbsoluteFile(), Document.class);
        Set<Annotation> before = oldDocument.getDocumentElement().getFrontMatter().getTitleText().getAnnotations();
        Set<Annotation> beforeSet = new HashSet<>(before);
        ObjectReader objectReader = mapper.readerForUpdating(oldDocument);
        Document updatedDocument = objectReader.readValue(json1);
        Set<Annotation> after = updatedDocument.getDocumentElement().getFrontMatter().getTitleText().getAnnotations();
        Set<Annotation> afterSet = new HashSet<>(after);
        assertTrue( "Annotation set is not larger after merge", beforeSet.size()< afterSet.size());


    }
}
