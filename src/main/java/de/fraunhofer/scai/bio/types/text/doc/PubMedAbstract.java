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
package de.fraunhofer.scai.bio.types.text.doc;

import java.io.Serializable;
import java.util.UUID;

import de.fraunhofer.scai.bio.types.text.doc.container.BackMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.BodyMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.FrontMatter;
import de.fraunhofer.scai.bio.types.text.doc.meta.MetaElement;

/**
 * Document Elements are the scaffold of every document. Every document element
 * encapsulates a specific (small) domain model and holds attributes of the
 * model. Examples are chapter, paragraph, figure, ... They are organized in
 * three subtypes: structure, container and meta.
 * 
 */
public class PubMedAbstract implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8358171049979076895L;
    private MetaElement metaElement;

    private UUID uuid = UUID.randomUUID();
    
    /**
     * Default constructor.
     */
    public PubMedAbstract() {
    }

    /**
     * @return the metaElement
     */
    public MetaElement getMetaElement() {
	return metaElement;
    }

    /**
     * @param metaElement the metaElement to set
     */
    public void setMetaElement(MetaElement metaElement) {
	this.metaElement = metaElement;
    }
    
    
}
