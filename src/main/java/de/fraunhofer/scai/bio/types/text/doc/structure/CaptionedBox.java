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

import lombok.Data;

/**
 * These boxes in documents contain usually pictures or tabular data and usually
 * have a numbering and a caption. Examples are figure, table, code or formular
 * (math).
 */
@Data public class CaptionedBox implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3952148080008882699L;
    private TextElement title;
    private int numbering;
    private TextElement caption;

}

