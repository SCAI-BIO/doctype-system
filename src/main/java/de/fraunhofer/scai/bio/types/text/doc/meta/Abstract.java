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

import de.fraunhofer.scai.bio.types.text.doc.container.Section;

/**
 * Abstract of a document.
 */
public class Abstract implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3867798877915368582L;
    private List<Section> abstractSections;

    /**
     * @return the abstractSections
     */
    public List<Section> getAbstractSections() {
	return abstractSections;
    }

    /**
     * @param abstractSection the abstract {@link Section} to add
     */
    public void addAbstractSection(Section abstractSection) {
	if (this.abstractSections == null)
	    this.abstractSections = new ArrayList<Section>();
	this.abstractSections.add(abstractSection);
    }

    /**
     * @param abstractSections the abstract {@link Section}s to set
     */
    public void setAbstractSections(List<Section> abstractSections) {
	this.abstractSections = abstractSections;
    }

}
