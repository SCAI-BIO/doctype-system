/*
 * Copyright 2018 Fraunhofer Institute SCAI, St. Augustin, Germany
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

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.fraunhofer.scai.bio.types.text.doc.DocumentElement;
import de.fraunhofer.scai.bio.types.text.doc.container.StructureElement;
import de.fraunhofer.scai.bio.types.text.doc.structure.Provenance;

/**
 * @author marc
 * @author klein
 *
 *         central datastructure of our DMS
 */
public class Document {

    public static final int ABSTRACT_LENGTH = 250;

    protected static Logger logger = LoggerFactory.getLogger(Document.class);

    private Provenance provenance;
    private DocumentElement documentElement;
    private String docType;

    @JsonIgnore
    private Map<String, StructureElement> structureElementIndex; // a quick access to all StructureElments and their
								 // Annotations

    /**
     * constructor
     */
    public Document() {
	this.provenance = new Provenance();
	this.setStructureElementIndex(new TreeMap<String, StructureElement>());
    }

    /**********************************************************************
     * getter and setter
     **********************************************************************/

    public DocumentElement getDocumentElement() {
	return documentElement;
    }

    public Provenance getProvenance() {
	return provenance;
    }

    public void setProvenance(Provenance provenance) {
	this.provenance = provenance;
    }

    public void setDocumentElement(DocumentElement documentElement) {
	this.documentElement = documentElement;
    }

    public String getDocType() {
	return docType;
    }

    public void setDocType(String docType) {
	this.docType = docType;
    }

    /**
     * escapes everything for HTML and replaces EOL of a substring
     * 
     * @param text  <code>String</code>
     * @param begin <code>int</code>
     * @param end   <code>int</code>
     * @return <code>String</code>
     */
    public String toHTML(String text, int begin, int end) {

	String html = StringEscapeUtils.escapeHtml(text.substring(begin, end)).replaceAll("\\r\\n", "\n")
		.replaceAll("\\r", "\n").replaceAll("\n", "<br>");

	logger.trace(html);

	return html;
    }

    /**
     * programming logic
     */

    
    public String toJsonString() {

	// Convert object to JSON string
	try {
	    ObjectMapper myObjectMapper = new ObjectMapper();

//	    myObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    return myObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);

	} catch (JsonProcessingException e) {
	    logger.error(e.getMessage());
	    logger.debug(ExceptionUtils.getStackTrace(e));
	    return null;
	}
    }

    public Map<String, StructureElement> getStructureElementIndex() {
	return structureElementIndex;
    }

    public void setStructureElementIndex(Map<String, StructureElement> structureElementIndex) {
	this.structureElementIndex = structureElementIndex;
    }

    public void addToStructureElementIndex(String key, StructureElement structureElement) {
	this.structureElementIndex.put(key, structureElement);
    }

}
