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
 * This represents code listings within a document. It can be code for all
 * programming languages or pseudo code. Write into the rhetorical attribute if
 * it's c, java, pseudo, latex or something like that.
 */
public class Code implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -871863145178162566L;

    private TextElement code;

    /**
     * @return the code
     */
    public TextElement getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(TextElement code) {
        this.code = code;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return code.getText();
    }

}
