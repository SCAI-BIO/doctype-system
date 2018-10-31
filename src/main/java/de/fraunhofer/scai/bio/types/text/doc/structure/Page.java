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
 * If a specific page should start, wrap the concerning document elements into
 * the page.
 */
public class Page implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8710743034102291637L;
    int pageNumber;
    String layout;

    /**
     * getter for layout - specifies the page layout. Examples: 2-column, titlepage
     * or standard.
     * 
     * 
     * @return layout of the feature
     */
    public String getLayout() {
	return this.layout;
    }

    /**
     * getter for pageNumber - gets The number of the page in the original artifact
     * 
     * 
     * @return page number of the feature
     */
    public int getPageNumber() {
	return this.pageNumber;
    }

    /**
     * setter for layout - specifices the page layout. Examples: 2-column, titlepage
     * or standard.
     * 
     * 
     * @param layout value to set into the feature
     */
    public void setLayout(String layout) {
	this.layout = layout;
    }

    /**
     * setter for pageNumber - sets The number of the page in the original artifact
     * 
     * 
     * @param pageNumber value to set into the feature
     */
    public void setPageNumber(int pageNumber) {
	this.pageNumber = pageNumber;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return String.format("%d\t%s", pageNumber, layout);
    }

}
