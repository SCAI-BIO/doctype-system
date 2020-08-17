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

import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;

import lombok.Data;

/**
 * A self-contained unit of discourse that deals with a particular point or
 * idea. (DoCO)
 */
@Data public class Paragraph implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8095267361703765778L;

    @Deprecated
    private Set<Sentence> sentences;
    private Set<StructureElement> structureElements;

    /**
     * @param structureElement the {@link StructureElement} to add
     */
    public void addStructureElement(StructureElement structureElement) {
        if (this.structureElements == null) {
            this.structureElements = new HashSet<StructureElement>();
        }
        this.structureElements.add(structureElement);
    }

    /**
     * @param sentences the {@link Sentence} to add
     */
    @Deprecated
    public void addSentence(Sentence sentence) {
        if (this.sentences == null) {
            this.sentences = new HashSet<Sentence>();
        }
        this.sentences.add(sentence);
    }

}
