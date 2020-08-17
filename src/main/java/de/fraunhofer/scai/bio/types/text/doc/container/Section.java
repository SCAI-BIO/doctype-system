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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import lombok.Data;

/**
 * The order is Part, Chapter, Section, SubSection, SubSubSection.
 */
@Data public class Section implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2516877768148991422L;
    private TextElement sectionRhetorical;
    private TextElement sectionTitle;
    private Set<Paragraph> paragraphs;
    private int depth = 0;

    /**
     * @return the rhetorical
     */
    @Deprecated
    public TextElement getRhetorical() {
        return sectionRhetorical;
    }

    /**
     * @param rhetorical the rhetorical to set
     */
    @Deprecated
    public void setRhetorical(TextElement sectionRhetorical) {
        this.sectionRhetorical = sectionRhetorical;
    }

    /**
     * @return the sectionTitle
     */
    @Deprecated
    public TextElement getTitle() {
        return sectionTitle;
    }

    /**
     * @param sectionTitle the sectionTitle to set
     */
    @Deprecated
    public void setTitle(TextElement sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    /**
     * @param paragraph the {@link Paragraph} to add
     */
    public void addParagraph(Paragraph paragraph) {
        if (this.paragraphs == null) {
            this.paragraphs = new HashSet<>();
        }
        this.paragraphs.add(paragraph);
    }

}
