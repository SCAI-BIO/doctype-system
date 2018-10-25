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

import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliography;

/**
 * The final principle part of a document, in which is usually found the
 * bibliography, index, appendixes, etc. (DoCO)
 */
public class BackMatter implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 242839067331745958L;

    private Bibliography bibliography;

    /**
     * @return the bibliography
     */
    public Bibliography getBibliography() {
	return bibliography;
    }

    /**
     * @param bibliography the bibliography to set
     */
    public void setBibliography(Bibliography bibliography) {
	this.bibliography = bibliography;
    }

}
