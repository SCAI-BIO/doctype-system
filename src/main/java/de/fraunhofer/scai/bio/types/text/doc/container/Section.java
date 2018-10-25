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
public class Section implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2516877768148991422L;
    private List<SubSection> subSections;
    private TextElement sectionRhetorical;
    private TextElement sectionTitle;
    
    /**
     * @return the rhetorical
     */
    public TextElement getRhetorical() {
        return sectionRhetorical;
    }

    /**
     * @param rhetorical the rhetorical to set
     */
    public void setRhetorical(TextElement sectionRhetorical) {
        this.sectionRhetorical = sectionRhetorical;
    }

    /**
     * @return the sectionTitle
     */
    public TextElement getTitle() {
        return sectionTitle;
    }

    /**
     * @param sectionTitle the sectionTitle to set
     */
    public void setTitle(TextElement sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    /**
     * @return the subSections
     */
    public List<SubSection> getSubSections() {
	return subSections;
    }

    /**
     * @param subSections the subSections to set
     */
    public void addSubSection(SubSection subSection) {
	if (this.subSections == null)
	    this.subSections = new ArrayList<SubSection>();
	this.subSections.add(subSection);
    }
    
    /**
     * @param subSections the subSections to set
     */
    public void setSubSections(List<SubSection> subSections) {
	this.subSections = subSections;
    }

}
