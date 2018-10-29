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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return getText();
    }
}
