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
 * Figures containing images and describe them in a caption.
 */
public class Figure implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4281623493434656899L;

    private TextElement rhetorical;
    private TextElement title;
    private TextElement caption;
    private ImageContent imageContent;

    /**
     * @return the rhetorical
     */
    public TextElement getRhetorical() {
        return rhetorical;
    }

    /**
     * @param rhetorical the rhetorical to set
     */
    public void setRhetorical(TextElement rhetorical) {
        this.rhetorical = rhetorical;
    }

    /**
     * @return the title
     */
    public TextElement getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(TextElement title) {
        this.title = title;
    }
    /**
     * @return the caption
     */
    public TextElement getCaption() {
	return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(TextElement caption) {
	this.caption = caption;
    }

    /**
     * getter for image - gets
     * 
     * 
     * @return value of the feature
     */
    public ImageContent getImage() {
	return imageContent;
    }

    /**
     * setter for image - sets
     * 
     * 
     * @param v value to set into the feature
     */
    public void setImage(ImageContent v) {
	this.imageContent = v;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return String.format("%s\n%s\n%s", title.getText(), imageContent.toString(), caption.getText());
    }

}
