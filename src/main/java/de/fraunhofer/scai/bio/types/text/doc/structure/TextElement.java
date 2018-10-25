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
package de.fraunhofer.scai.bio.types.text.documentelement.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 *  */
public class TextElement implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9172199956790819065L;
    private List<Annotation> annotations;    
    
    /**
     * The text (plain text without any other object mapping).
     */
    private String text;

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

}
