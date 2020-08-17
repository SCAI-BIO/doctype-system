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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

/**
 * Specifies the document outline. Examples are chapters, sections, and so on. The outline-order of common (large) books
 * is Part, Chapter, Section, SubSection, SubSubSection. The outline-order of common for scientific works like thesis
 * are Chapter, Section, SubSection, SubSubSection. If you've got a very deeply resp. uncommon outlined text, with
 * section-headings like '1.1.1.2.3 heading titleText', you can use the raw Outline document element and nest it. So you
 * have a generic way to express any outline-depth.
 */
@Data public class Outline implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4868157788079288706L;
    private TextElement titleText;
    private Set<String> numbering;
    private TextElement rhetorical;

    private Set<String> parts;
    private Map<String, Set<String>> chapters;
    private Map<String, Set<String>> sections;

    /**
     * @param numbering The numbering to add
     */
    public void addNumbering(String numbering) {
        if (this.numbering == null) {
            this.numbering = new HashSet<>();
        }
        this.numbering.add(numbering);
    }

}
