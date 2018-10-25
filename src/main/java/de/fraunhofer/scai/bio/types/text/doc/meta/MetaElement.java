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
package de.fraunhofer.scai.bio.types.text.doc.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This document elements only describe other elements or external ressources,
 * like the autor or references.
 */
public class MetaElement implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4598961834730776036L;
    private Bibliographic bibliographic;
    private Bibliography bibliography;
    private List<Keywords> keywords;
    private Concept concept;

    /**
     * Default constructor.
     */
    public MetaElement() {
    }

    /**
     * @param keywords the keywords to set
     */
    public void addKeywords(Keywords keywordList) {
	if (this.keywords == null) 
	    this.keywords = new ArrayList<Keywords>();
	getKeywords().add(keywordList);
    }

    /**
     * getter methods
     */
    public Bibliographic getBibliographic() {
	return this.bibliographic;
    }

    public Bibliography getBibliography() {
	return this.bibliography;
    }

    /**
     * @return the concept
     */
    public Concept getConcept() {
	return concept;
    }

    /**
     * @return the keywords
     */
    public List<Keywords> getKeywords() {
	return keywords;
    }

    /**
     * setter methods
     * 
     */
    public void setBibliographic(Bibliographic bib) {
	this.bibliographic = bib;
    }

    public void setBibliography(Bibliography bib) {
	this.bibliography = bib;
    }

    /**
     * @param concept the concept to set
     */
    public void setConcept(Concept concept) {
	this.concept = concept;
    }

    public void setDocumentConcept(Concept concept) {
	this.concept = concept;

    }
    
    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(List<Keywords> keywords) {
	this.keywords = keywords;
    }
}
