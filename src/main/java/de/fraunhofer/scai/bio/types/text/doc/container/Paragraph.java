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

import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;
import de.fraunhofer.scai.bio.types.text.doc.structure.StructureElement;

/**
 * A self-contained unit of discourse that deals with a particular point or
 * idea. (DoCO)
 */
public class Paragraph implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8095267361703765778L;

    private List<StructureElement> structureElements;

    /**
     * @return the paragraphSentences
     */
    public List<StructureElement> geStructureElements() {
	return structureElements;
    }

    /**
     * @param paragraphSentence the {@link Sentence} to set
     */
    public void addStructureElement(StructureElement structureElement) {
	if (this.structureElements == null)
	    this.structureElements = new ArrayList<StructureElement>();
	this.structureElements.add(structureElement);
    }
    
    /**
     * @param paragraphSentences the paragraphSentences to set
     */
    public void setStructureElements(List<StructureElement> structureElements) {
	this.structureElements = structureElements;
    }
    
    
}
