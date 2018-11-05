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
 * These boxes in documents contain usually pictures or tabular data and usually
 * have a numbering and a caption. Examples are figure, table, code or formular
 * (math).
 */
public class CaptionedBox implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3952148080008882699L;
    private TextElement title;
    private int numbering;
    private TextElement caption;

    /**
     * getter for caption - gets All captioned boxes have captions describing the
     * contents of the box with natural langugage. Example: Figure 1: caption text.
     * 
     * @return caption of the {@link CaptionedBox}
     */
    public TextElement getCaption() {
	return this.caption;
    }

    /**
     * getter for numbering - gets All captioned boxes are numbered. Example Figure
     * 1: caption text.
     * 
     * @return numbering of the {@link CaptionedBox}
     */
    public int getNumbering() {
	return this.numbering;
    }

    /**
     * getter for titleText - Some captioned boxes have titles.
     * 
     * @return the title of the {@link CaptionedBox}
     */
    public TextElement getTitle() {
	return this.title;
    }

    /**
     * setter for caption - sets All captioned boxes have captions describing the
     * contents of the box with natural langugage. Example: Figure 1: caption text.
     * 
     * @param caption of the {@link CaptionedBox} to set
     */
    public void setCaption(TextElement caption) {
	this.caption = caption;
    }

    /**
     * setter for numbering - sets All captioned boxes are numbered. Example Figure
     * 1: caption text.
     * 
     * @param numbering value of the {@link CaptionedBox} to set
     */
    public void setNumbering(int numbering) {
	this.numbering = numbering;
    }

    /**
     * setter for titleText - sets Some captioned boxes have titles.
     * 
     * @param title of the {@link CaptionedBox}
     */
    public void setTitle(TextElement title) {
	this.title = title;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
	return String.format("%d\t%s\n%s", numbering, title.getText(), caption.getText());
    }

}

