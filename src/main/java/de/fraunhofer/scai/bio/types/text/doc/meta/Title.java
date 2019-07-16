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
 * Title of a document. It can also contain a subtitle.
 */
public class Title implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 441162620093039893L;
    private TextElement titleText;
    private TextElement subTitleText;

    /**
     * getter for subtitle - gets
     *
     * @return the subtitle
     */
    public TextElement getSubtitleText() {
        return this.subTitleText;
    }

    /**
     * setter for subtitle - sets
     *
     * @param subTitleText value to set into the feature
     */
    public void setSubtitleText(TextElement subTitleText) {
        this.subTitleText = subTitleText;
    }

    /**
     * getter for titleText - gets
     *
     * @return the title text
     */
    public TextElement getTitleText() {
        return this.titleText;
    }

    /**
     * setter for titleText - sets
     *
     * @param titleText the title text
     */
    public void setTitleText(TextElement titleText) {
        this.titleText = titleText;
    }
}
