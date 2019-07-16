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
 * A formula can be a math, physical, chemical or something like that.
 */
public class Formula implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -226821519360592861L;

    private TextElement formula;

    /**
     * @return the formula
     */
    public TextElement getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(TextElement formula) {
        this.formula = formula;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return formula.getText();
    }
}
