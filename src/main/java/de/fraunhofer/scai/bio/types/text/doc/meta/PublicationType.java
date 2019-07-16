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

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import java.io.Serializable;

/**
 * @author klein
 */
public class PublicationType implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -413014484812578059L;
    private TextElement identifier;
    private TextElement publicationType;

    /**
     * @return the identifier
     */
    public TextElement getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(TextElement identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the publicationType
     */
    public TextElement getPublicationType() {
        return publicationType;
    }

    /**
     * @param publicationType the publicationType to set
     */
    public void setPublicationType(TextElement publicationType) {
        this.publicationType = publicationType;
    }


}
