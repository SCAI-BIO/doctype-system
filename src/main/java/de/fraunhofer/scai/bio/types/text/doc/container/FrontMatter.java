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
package de.fraunhofer.scai.bio.types.text.doc.container;

import java.io.Serializable;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * The initial principle part of a document, usually containing self-referential
 * metadata. In a book, this typically includes its titleText, authors,
 * publisher, publication date, ISBN and copyright declaration, together with
 * the preface, foreword, table of content, etc. In a journal article, the front
 * matter is normally restricted to the titleText, authors and the authors'
 * affiliation details, although the latter may alternatively be included in a
 * footnote or the back matter. In books, the front matter pages may be numbered
 * in lowercase Roman numerals. (DoCO)
 */
public class FrontMatter implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6851482782475430729L;

    private TextElement titleText;

    /**
     * @return the titleText 
     */
    public TextElement getTitleText() {
	return titleText;
    }

    /**
     * @param titleText the titleText to set
     */
    public void setTitleText(TextElement titleText) {
	this.titleText = titleText;
    }

}
