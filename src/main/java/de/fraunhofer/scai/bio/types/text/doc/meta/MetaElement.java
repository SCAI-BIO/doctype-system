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

import lombok.Data;

/**
 * This document elements only describe other elements or external ressources,
 * like the autor or references.
 */
@Data public class MetaElement implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4598961834730776036L;
    private Bibliographic bibliographic;
    @Deprecated // is already in backmatter contained
    private Bibliography bibliography;
    private List<Keywords> keywords;
    private Concept concept;

    /**
     * @param keywords the keywords to set
     */
    public void addKeywords(Keywords keywordList) {
        if (this.keywords == null) {
            this.keywords = new ArrayList<Keywords>();
        }
        getKeywords().add(keywordList);
    }

}
