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
package de.fraunhofer.scai.bio.types.text.doc.container;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * The order is Part, Chapter, Section, SubSection, SubSubSection.
 */
public class SubSection implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3972328389264019910L;
    private TextElement subSectionTitle;
    private TextElement subSectionRhetorical;
    private List<SubSubSection> subSubSections;
    private List<Paragraph> paragraphs;

    /**
     * @return the title
     */
    public TextElement getTitle() {
	return subSectionTitle;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(TextElement subSectionTitle) {
	this.subSectionTitle = subSectionTitle;
    }

    /**
     * @return the rhetorical
     */
    public TextElement getRhetorical() {
        return subSectionRhetorical;
    }

    /**
     * @param rhetorical the rhetorical to set
     */
    public void setRhetorical(TextElement subSectionRhetorical) {
        this.subSectionRhetorical = subSectionRhetorical;
    }

    /**
     * @return the subSubSections
     */
    public List<SubSubSection> getSubSubSections() {
	return subSubSections;
    }

    /**
     * @param subSubSection the {@link SubSubSection} to set
     */
    public void addSubSubSection(SubSubSection subSubSection) {
	if (this.subSubSections == null)
	    this.subSubSections = new ArrayList<SubSubSection>();
	this.subSubSections.add(subSubSection);
    }
    
    /**
     * @param subSubSections the subSubSections to set
     */
    public void setSubSubSections(List<SubSubSection> subSubSections) {
	this.subSubSections = subSubSections;
    }
    

    /**
     * @return the paragraphs
     */
    public List<Paragraph> getParagraphs() {
	return paragraphs;
    }

    /**
     * @param paragraph the {@link Paragraph} to set
     */
    public void addParagraph(Paragraph paragraph) {
	if (this.paragraphs == null)
	    this.paragraphs = new ArrayList<Paragraph>();
	this.paragraphs.add(paragraph);
    }
    
    /**
     * @param paragraphs the paragraphs to set
     */
    public void setParagraphs(List<Paragraph> paragraphs) {
	this.paragraphs = paragraphs;
    }
}
