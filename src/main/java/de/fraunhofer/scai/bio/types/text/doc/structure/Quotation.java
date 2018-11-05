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

import de.fraunhofer.scai.bio.types.text.doc.meta.Reference;

/**
 * A quotation block which cites another document.
 */
public class Quotation implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8147702454907782570L;
    private Reference reference;

    /**
     * getter for reference - gets
     * 
     * @return value of the feature
     */
    public Reference getReference() {
	return this.reference;
    }

    /**
     * setter for reference - sets
     * 
     * @param v value to set into the feature
     */
    public void setReference(Reference v) {
	this.reference = v;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return reference.toString();
    }

}
