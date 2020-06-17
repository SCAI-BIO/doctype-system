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

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import lombok.Data;

/**
 * @author klein
 */
@Data public class Person implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1899080439065479768L;
    private TextElement surname;
    private TextElement forename;
    private Affiliation affiliation;
    private TextElement degree;

}
