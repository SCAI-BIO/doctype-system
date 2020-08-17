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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import lombok.Data;

/**
 * @author klein
 */
@Data public class Keywords implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6068148947394926182L;
    private TextElement rhetorical;
    private Set<TextElement> keywordList;

    public Keywords() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Adds a keyword to the {@link List} of keywords.
     *
     * @param keyword the keyword to add
     */
    public void addKeyword(TextElement keyword) {
        if (this.keywordList == null) {
            this.keywordList = new HashSet<>();
        }
        this.keywordList.add(keyword);
    }

}
