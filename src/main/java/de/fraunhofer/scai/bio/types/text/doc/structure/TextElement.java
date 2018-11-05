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
import java.util.List;
import java.util.UUID;

import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;

/** 
 *  */
public class TextElement implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9172199956790819065L;
    
    /**
     * The text (plain text without any other object mapping).
     */
    private String text;
    
    private UUID uuid;
    
    private List<Annotation> annotations;

    /**
     * @return the text
     */
    public String getText() {
	return text;
    }

    /**
     * @param textElement the textElement to set
     */
    public void setText(String text) {
	this.text = text;
    }
    
    /**
     * @return the annotations
     */
    public List<Annotation> getAnnotations() {
	return annotations;
    }

    /**
     * @param annotation the annotation to add
     */
    public void addAnnotation(Annotation annotation) {
	if (this.annotations == null)
	    this.annotations = new ArrayList<Annotation>();
	this.annotations.add(annotation);
    }

    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(List<Annotation> annotations) {
	this.annotations = annotations;
    }

    /**
     * @return the uuid
     */
    public UUID getUuid() {
	if (this.uuid == null)
	    setUuid();
	return this.uuid;
    }

    /**
     * Creates a new {@link UUID}.
     */
    public void setUuid() {
        this.uuid = UUID.randomUUID();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return getText();
    }
}
