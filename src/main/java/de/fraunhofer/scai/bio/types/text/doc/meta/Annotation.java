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

import de.fraunhofer.scai.bio.Provenance;

/**
 * @author klein
 *
 */
public class Annotation implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7162485139551328806L;
    private int startOffset;
    private int endOffset;
    private String annotationType;
    private String annotationText;

    private Provenance provenance;
    
    /**
     * @return the provenance
     */
    public Provenance getProvenance() {
        return provenance;
    }

    /**
     * @param provenance the provenance to set
     */
    public void setProvenance(Provenance provenance) {
        this.provenance = provenance;
    }

    /**
     * @return the startOffset
     */
    public int getStartOffset() {
	return startOffset;
    }

    /**
     * @param startOffset the startOffset to set
     */
    public void setStartOffset(int startOffset) {
	this.startOffset = startOffset;
    }

    /**
     * @return the endOffset
     */
    public int getEndOffset() {
	return endOffset;
    }

    /**
     * @param endOffset the endOffset to set
     */
    public void setEndOffset(int endOffset) {
	this.endOffset = endOffset;
    }

    /**
     * @return the annotationType
     */
    public String getAnnotationType() {
	return annotationType;
    }

    /**
     * @param annotationType the annotationType to set
     */
    public void setAnnotationType(String annotationType) {
	this.annotationType = annotationType;
    }

    /**
     * @return the annotationText
     */
    public String getAnnotationText() {
	return annotationText;
    }

    /**
     * @param annotationText the annotationText to set
     */
    public void setAnnotationText(String annotationText) {
	this.annotationText = annotationText;
    }

}
