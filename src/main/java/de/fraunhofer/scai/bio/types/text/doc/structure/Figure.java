/*
 * Copyright 2018 Fraunhofer Institute SCAI, St. Augustin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;

import lombok.Data;

/**
 * Figures containing images and describe them in a caption.
 */
@Data public class Figure implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4281623493434656899L;

    private TextElement rhetorical;
    private TextElement title;
    private TextElement caption;
    private ImageContent imageContent;

    /**
     * getter for image - gets
     *
     * @return value of the feature
     */
    @Deprecated
    public ImageContent getImage() {
        return imageContent;
    }

    /**
     * setter for image - sets
     *
     * @param v value to set into the feature
     */
    @Deprecated
    public void setImage(ImageContent v) {
        this.imageContent = v;
    }

}
