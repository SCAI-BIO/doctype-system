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
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

/**
 * A captioned table.
 */
@Data public class Table implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6457211594148660263L;
    private TextElement caption;
    private TextElement title;
    private TextElement text;
    private TextElement rhetorical;
    private Set<TextElement> headers;
    private Set<String> annotatedHeaders;
    private Set<Set<TextElement>> columns;
    private Set<Map<String, String>> rows;
    private String tableId;
    private TextElement documentId;


}
