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
package de.fraunhofer.scai.bio.types.text.documentelement.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.scai.bio.types.text.documentelement.container.Section;
import de.fraunhofer.scai.bio.types.text.documentelement.structure.TextElement;

/**
 * Abstract of a document.
 */
public class Abstract implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3867798877915368582L;
    private TextElement abstractText;
    private List<Section> abstractSections;
    
    /**
     * @return the abstractText
     */
    public TextElement getAbstractText() {
	return abstractText;
    }

    /**
     * @param abstractText the abstractText to set
     */
    public void setAbstractText(TextElement abstractText) {
	this.abstractText = abstractText;
    }

    /**
     * @return the abstractSection
     */
    public List<Section> getAbstractSections() {
	return abstractSections;
    }

    /**
     * @param abstractSection the abstractSection to set
     */
    public void addAbstractSection(Section abstractSection) {
	if (this.abstractSections == null)
	    this.abstractSections = new ArrayList<Section>();
	this.abstractSections.add(abstractSection);
    }

    /**
     * @param abstractSections the abstractSections to set
     */
    public void setAbstractSections(List<Section> abstractSections) {
        this.abstractSections = abstractSections;
    }

}
