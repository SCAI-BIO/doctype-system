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

/**
 * The bibliography contains all literature references which are made in the
 * document. Synonyms are references or list of literature.
 */
public class Bibliography implements Serializable {
	
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3505381016659827384L;
    private Reference reference;
    private List<Reference> references;

    // *--------------*
    // * Feature: references

    /**
     * Add {@link Reference} element.
     * 
     * @param reference {@link Reference} to add to the {@link List} of
     *                  {@link Reference}s
     */
    public void addReference(Reference reference) {
	if (references == null)
	    references = new ArrayList<Reference>();
	references.add(reference);
    }

    public Reference getReference() {
		return reference;
	}

	/**
     * Getter for {@link Reference}s.
     * 
     * 
     * @return the {@link List} of {@link Reference}s
     */
    public List<Reference> getReferences() {
	return references;
    }

    public void setReference(Reference reference) {
	this.reference = reference;
    }

    /**
     * setter for {@link Reference}s.
     * 
     * 
     * @param references the {@link List} of {@link Reference}s to set
     */
    public void setReferences(List<Reference> references) {
	this.references = references;
    }
}
