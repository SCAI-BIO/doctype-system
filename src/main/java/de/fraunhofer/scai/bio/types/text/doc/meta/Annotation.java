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

/**
 * @author klein
 *
 */
public class Annotation {

    private int startOffset;
    private int endOffset;
    private TextElement annotationType;
    private TextElement annotationText;

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
    public TextElement getAnnotationType() {
	return annotationType;
    }

    /**
     * @param annotationType the annotationType to set
     */
    public void setAnnotationType(TextElement annotationType) {
	this.annotationType = annotationType;
    }

    /**
     * @return the annotationText
     */
    public TextElement getAnnotationText() {
	return annotationText;
    }

    /**
     * @param annotationText the annotationText to set
     */
    public void setAnnotationText(TextElement annotationText) {
	this.annotationText = annotationText;
    }

}
