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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import lombok.Data;

/**
 * The bibliography contains all literature references which are made in the document. Synonyms are references or list
 * of literature.
 */
@Data public class Bibliography implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3505381016659827384L;
    private Map<String, Reference> references;
    private TextElement title;

    /**
     * Add {@link Reference} element.
     *
     * @param reference {@link Reference} to add to the {@link List} of
     *                  {@link Reference}s
     */
    public void addReference(String id, Reference reference) {
        if (references == null) {
            references = new LinkedHashMap<String, Reference>();
        }
        references.put(id, reference);
    }

}
