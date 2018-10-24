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
package de.fraunhofer.scai.bio.types.text.documentelement.structure;

import java.io.Serializable;

/**
 * A captioned table.
 */
public class Table implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6457211594148660263L;
    private String caption;
    private String title;
    private String text;
    private String rhetorical;

    /**
     * @return the caption
     */
    public String getCaption() {
	return caption;
    }

    /**
     * @return the text
     */
    public String getText() {
	return text;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @return the rhetorical
     */
    public String getRhetorical() {
        return rhetorical;
    }

    /**
     * @param rhetorical the rhetorical to set
     */
    public void setRhetorical(String rhetorical) {
        this.rhetorical = rhetorical;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public void setText(String text) {
	this.text = text;
    }

    public void setTitle(String title) {
	this.title = title;
    }

}
