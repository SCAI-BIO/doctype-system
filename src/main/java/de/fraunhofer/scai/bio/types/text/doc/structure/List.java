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

/**
 * These boxes in documents contain usually pictures or tabular data and usually
 * have a numbering and a caption. Examples are figure, table, code or formula
 * (math).
 */
public class List implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3952148080008882699L;
    private TextElement title;
    private java.util.List<TextElement> items;
    private boolean bullets;

    /**
     * getter for caption - gets All captioned boxes have captions describing the
     * contents of the box with natural language. Example: Figure 1: caption text.
     *
     * @return caption of the {@link List}
     */
    public java.util.List<TextElement> getItems() {
        return this.items;
    }

    /**
     * setter for items of a list
     *
     * @param caption of the {@link List} to set
     */
    public void setItems(java.util.List<TextElement> items) {
        this.items = items;
    }

    /**
     * getter for titleText - Some captioned boxes have titles.
     *
     * @return the title of the {@link List}
     */
    public TextElement getTitle() {
        return this.title;
    }

    /**
     * setter for titleText - sets Some captioned boxes have titles.
     *
     * @param title of the {@link List}
     */
    public void setTitle(TextElement title) {
        this.title = title;
    }

    public void addItem(TextElement item) {
        if (this.items == null) {
            this.items = new ArrayList<TextElement>();
        }
        this.items.add(item);
    }

    public boolean isBullets() {
        return bullets;
    }

    public void setBullets(boolean bullets) {
        this.bullets = bullets;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%d%s\n", title.getText()));
        for (TextElement item : items) {
            sb.append("\t" + item.getText() + "\n");
        }
        return sb.toString();
    }
}

