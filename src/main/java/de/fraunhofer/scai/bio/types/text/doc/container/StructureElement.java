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

import de.fraunhofer.scai.bio.types.text.doc.structure.CaptionedBox;
import de.fraunhofer.scai.bio.types.text.doc.structure.Code;
import de.fraunhofer.scai.bio.types.text.doc.structure.DataTable;
import de.fraunhofer.scai.bio.types.text.doc.structure.Figure;
import de.fraunhofer.scai.bio.types.text.doc.structure.Formula;
import de.fraunhofer.scai.bio.types.text.doc.structure.ImageContent;
import de.fraunhofer.scai.bio.types.text.doc.structure.List;
import de.fraunhofer.scai.bio.types.text.doc.structure.PMCTable;
import de.fraunhofer.scai.bio.types.text.doc.structure.Outline;
import de.fraunhofer.scai.bio.types.text.doc.structure.Quotation;
import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;
import de.fraunhofer.scai.bio.types.text.doc.structure.Table;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import lombok.Data;

@Data
public class StructureElement implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7490022742872925687L;
    private CaptionedBox captionedBox;
    private Code code;
    private DataTable dataTable;
    private Figure figure;
    private Formula formula;
    private ImageContent imageContent;
    private Outline outline;
    private Quotation quotation;
    @Deprecated
    private Table table;
    private PMCTable pmcTable;
    private TextElement textElement;
    private Sentence sentence;
    private List list;

}
