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
package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.fraunhofer.scai.bio.types.text.doc.container.Chapter;
import de.fraunhofer.scai.bio.types.text.doc.container.Part;
import de.fraunhofer.scai.bio.types.text.doc.container.Section;
import de.fraunhofer.scai.bio.types.text.doc.container.SubSection;
import de.fraunhofer.scai.bio.types.text.doc.container.SubSubSection;

/**
 * Specifies the document outline. Examples are chapters, sections, and so on.
 * The outline-order of common (large) books is Part, Chapter, Section,
 * SubSection, SubSubSection. The outline-order of common for scientific works
 * like thesis are Chapter, Section, SubSection, SubSubSection. If you've got a
 * very deeply resp. uncommon outlined text, with section-headings like
 * '1.1.1.2.3 heading titleText', you can use the raw Outline document element
 * and nest it. So you have a generic way to express any outline-depth.
 */
public class Outline implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4868157788079288706L;
    String titleText;
    List<String> numbering;
    String rhetorical;

    private List<Part> parts;
    private Map<Part, List<Chapter>> chapters;
    private Map<Chapter, List<Section>> sections;
    private Map<Section, List<SubSection>> subSections;
    private Map<SubSection, List<SubSubSection>> subSubSections;

    /**
     * 
     * @param numbering The numbering to add
     */
    public void addNumbering(String numbering) {
	if (this.numbering == null)
	    this.numbering = new ArrayList<String>();
	this.numbering.add(numbering);
    }

    /**
     * @return the chapters
     */
    public Map<Part, List<Chapter>> getChapters() {
	return chapters;
    }

    /**
     * getter for numbering - gets Numbering of a outline. Subsection example: 1.2.1
     * Title. Numbering in this case is StringList('1', '2', '1').
     * 
     * 
     * @return numbering of the {@link Outline}
     */
    public List<String> getNumbering() {
	return numbering;
    }

    /**
     * @return the parts
     */
    public List<Part> getParts() {
	return parts;
    }

    /**
     * getter for rhetorical - gets
     * 
     * 
     * @return rhetorical of the feature
     */
    public String getRhetorical() {
	return this.rhetorical;
    }

    /**
     * @return the sections
     */
    public Map<Chapter, List<Section>> getSections() {
	return sections;
    }

    /**
     * @return the subSections
     */
    public Map<Section, List<SubSection>> getSubSections() {
	return subSections;
    }

    /**
     * @return the subSubSections
     */
    public Map<SubSection, List<SubSubSection>> getSubSubSections() {
	return subSubSections;
    }

    /**
     * @return the titleText
     */
    public String getTitleText() {
	return titleText;
    }

    /**
     * @param chapters the chapters to set
     */
    public void setChapters(Map<Part, List<Chapter>> chapters) {
	this.chapters = chapters;
    }

    /**
     * setter for numbering - sets Numbering of a outline. Subsection example: 1.2.1
     * Title. Numbering in this case is StringList('1', '2', '1').
     * 
     * @param numbering value to set into the feature
     */
    public void setNumbering(List<String> numbering) {
	this.numbering = numbering;
    }

    /**
     * @param parts the parts to set
     */
    public void setParts(List<Part> parts) {
	this.parts = parts;
    }

    /**
     * setter for rhetorical - sets
     * 
     * 
     * @param rhetorical value to set into the feature
     */
    public void setRhetorical(String rhetorical) {
	this.rhetorical = rhetorical;
    }

    /**
     * @param sections the sections to set
     */
    public void setSections(Map<Chapter, List<Section>> sections) {
	this.sections = sections;
    }

    /**
     * @param subSections the subSections to set
     */
    public void setSubSections(Map<Section, List<SubSection>> subSections) {
	this.subSections = subSections;
    }

    /**
     * @param subSubSections the subSubSections to set
     */
    public void setSubSubSections(Map<SubSection, List<SubSubSection>> subSubSections) {
	this.subSubSections = subSubSections;
    }

    /**
     * @param titleText the titleText to set
     */
    public void setTitleText(String titleText) {
	this.titleText = titleText;
    }

}
