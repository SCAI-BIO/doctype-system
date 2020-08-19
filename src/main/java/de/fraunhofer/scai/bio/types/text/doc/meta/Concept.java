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
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import lombok.Data;

/**
 * @author klein
 */
@Data public class Concept implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7443533790762224432L;
    @JsonProperty("prefLabel")
    private TextElement preferredLabel;
    private TextElement altLabel;
    private TextElement hiddenLabel;
    private TextElement identifier;

    private TextElement identifierSource;

    private TextElement[] identifierWithSource;
    private List<TextElement> altLabels;
    private List<TextElement> hiddenLabels;

    /**
     * @param altLabel the alternate label to set
     */
    public void addAltLabel(TextElement altLabel) {
        if (altLabels == null) {
            altLabels = new ArrayList<TextElement>();
        }
        altLabels.add(altLabel);
    }

    /**
     * TODO check index!!!
     *
     * @param index       index
     * @param hiddenLabel hidden label
     */
    public void addHiddenLabel(int index, TextElement hiddenLabel) {
        if (this.hiddenLabels == null) {
            this.hiddenLabels = new ArrayList<TextElement>();
        }
        this.hiddenLabels.add(hiddenLabel);
    }

    @JsonIgnore
    public TextElement getPrefLabel() {
        return this.preferredLabel;
    }

    public void setPrefLabel(TextElement label) {
        this.preferredLabel = label;
    }

}
