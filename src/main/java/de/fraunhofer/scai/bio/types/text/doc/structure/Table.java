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

/**
 * A captioned table.
 */
public class Table implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6457211594148660263L;
    private TextElement caption;
    private TextElement title;
    private TextElement text;
    private TextElement rhetorical;
    private List<TextElement> headers;
    private List<String> annotatedHeaders;
    private List<List<TextElement>> columns;
    private List<Map<String, String>> rows;
    private String tableId;
    private TextElement documentId;


    /**
     * @return the caption
     */
    public TextElement getCaption() {
        return caption;
    }

    public void setCaption(TextElement caption) {
        this.caption = caption;
    }

    /**
     * @return the text
     */
    public TextElement getText() {
        return text;
    }

    public void setText(TextElement text) {
        this.text = text;
    }

    /**
     * @return the title
     */
    public TextElement getTitle() {
        return title;
    }

    public void setTitle(TextElement title) {
        this.title = title;
    }

    /**
     * @return the rhetorical
     */
    public TextElement getRhetorical() {
        return rhetorical;
    }

    /**
     * @param rhetorical the rhetorical to set
     */
    public void setRhetorical(TextElement rhetorical) {
        this.rhetorical = rhetorical;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("%s", documentId);
    }


    public List<TextElement> getHeaders() {
        return headers;
    }

    public void setHeaders(List<TextElement> headers) {
        this.headers = headers;
    }


    public List<List<TextElement>> getColumns() {
        return columns;
    }

    public void setColumns(List<List<TextElement>> columns) {
        this.columns = columns;
    }


    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public List<String> getAnnotatedHeaders() {
        return annotatedHeaders;
    }

    public void setAnnotatedHeaders(List<String> annotatedHeaders) {
        this.annotatedHeaders = annotatedHeaders;
    }

    public TextElement getDocumentId() {
        return documentId;
    }

    public void setDocumentId(TextElement documentId) {
        this.documentId = documentId;
    }
}
