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

/**
 * The order is Part, Chapter, Section, SubSection, SubSubSection.
 */
public class Chapter implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1668864885851536488L;
    private List<Section> sections;

    /**
     * @param section the {@link Section} to set
     */
    public void addSection(Section section) {
	if (this.sections == null) {
	    this.sections = new ArrayList<Section>();
	}
	this.sections.add(section);
    }

    /**
     * @return the sections
     */
    public List<Section> getSections() {
	return sections;
    }

    /**
     * @param sections the sections to set
     */
    public void setSections(List<Section> sections) {
	this.sections = sections;
    }
}
