/*
 * Copyright 2019 Fraunhofer Institute SCAI, St. Augustin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.fraunhofer.scai.bio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.fraunhofer.scai.bio.types.text.doc.DocumentElement;
import de.fraunhofer.scai.bio.types.text.doc.container.StructureElement;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author marc
 * @author klein
 * <p>
 * Central datastructure of our DMS
 */
public class Document implements Serializable {

    public static final int ABSTRACT_LENGTH = 250;
    public static final String PUBMED_ABSTRACT = "PUBMED_ABSTRACT";
    public static final String PMC_FULLTEXT = "PMC_FULLTEXT";
    /**
     * serial version id.
     */
    private static final long serialVersionUID = 4813140736245257135L;
    protected static Logger logger = LoggerFactory.getLogger(Document.class);

    private Provenance provenance;
    private DocumentElement documentElement;
    private String docType;
    private String originalMimeType;

    @JsonIgnore
    private Map<UUID, TextElement> textElementIndex; // a quick access to all TextElements and their
    // Annotations

    /**
     * Constructor.
     */
    public Document() {
        this.provenance = new Provenance();
        this.setTextElementIndex(new TreeMap<UUID, TextElement>());
    }

    /**********************************************************************
     * getter and setter
     **********************************************************************/

    /**
     * @return the {@link DocumentElement}
     */
    public DocumentElement getDocumentElement() {
        return documentElement;
    }

    /**
     * Sets the {@link DocumentElement}
     *
     * @param documentElement the {@link DocumentElement}
     */
    public void setDocumentElement(DocumentElement documentElement) {
        this.documentElement = documentElement;
    }

    /**
     * @return the {@link Provenance}
     */
    public Provenance getProvenance() {
        return provenance;
    }

    /**
     * Sets the {@link Provenance}
     *
     * @param provenance the {@link Provenance}
     */
    public void setProvenance(Provenance provenance) {
        this.provenance = provenance;
    }

    /**
     * Returns the content type of the document, e.g. PubMedAbstract, PubMedArticle, UserDocument, ...
     *
     * @return docType the type
     */
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }


    /**
     * @return the index of the {@link StructureElement}s
     */
    public Map<UUID, TextElement> getTextElementIndex() {
        return textElementIndex;
    }

    /**
     * @param structureElementIndex the index of the {@link StructureElement}s
     */
    public void setTextElementIndex(Map<UUID, TextElement> textElementIndex) {
        this.textElementIndex = textElementIndex;
    }

    /**
     * Adds a {@link StructureElement} to the index
     *
     * @param key              The key of the {@link StructureElement}
     * @param structureElement the {@link StructureElement}
     */
    public void addToTextElementIndex(UUID key, TextElement structureElement) {
        this.textElementIndex.put(key, structureElement);
    }

    /**
     * @return the mimeType
     */
    public String getOriginalMimeType() {
        return originalMimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setOriginalMimeType(String originalMimeType) {
        this.originalMimeType = originalMimeType;
    }

}
